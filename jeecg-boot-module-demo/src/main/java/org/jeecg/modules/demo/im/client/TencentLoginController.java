package org.jeecg.modules.demo.im.client;

import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.demo.cms.manager.sysconfig.ISysConfigUtil;
import org.jeecg.modules.demo.im.manager.entity.ImUser;
import org.jeecg.modules.demo.im.manager.mapper.ImUserMapper;
import org.jeecg.modules.demo.utils.wechat.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author henry(zhanghai)
 * @date 2022-02-08 23:12
 */
@RestController
@RequestMapping("/im/client/tencent")
@Api(tags = "公众号授权登录接口")
@CrossOrigin // 跨域支持
@Slf4j
public class TencentLoginController {
    @Value("${tencent.appid}")
    private String appid;
    @Value("${tencent.callback}")
    private String http;
    @Autowired
    private ConfigUtil configUtil;
    @Resource
    private ImUserMapper imUserMapper;

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/login")
    @ApiOperation(value = "公众号授权登录")
    public Result doLogin() {
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + appid +
                "&redirect_uri=" + http +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        log.info(url + "公众号第一步请求url");
        return Result.OK(url);
    }

    @PostMapping("/pastDueToken")
    @ApiOperation(value = "根据token查看token过期情况")
    public Result pastDueToken(@RequestBody ImUserTokenDTO imUserTokenDTO) {
        Integer pastDueToken = imUserMapper.is_token_valid(imUserTokenDTO.getToken());
        if (pastDueToken == 1) {
            return Result.error520("token已经过期了，请重新登录！");
        }
        return Result.OK(pastDueToken);
    }

    @PostMapping("/userToken")
    @ApiOperation(value = "根据token查询当前用户信息")
    public Result userToken(@RequestBody ImUserTokenDTO imUserTokenDTO) {
        Integer checkUserToken = imUserMapper.checkToken(imUserTokenDTO.getToken());
        if (checkUserToken == 0) {
            return Result.error520("token不存在或已经过期了！");
        }
        ImUser imUserToken = imUserMapper.getToken(imUserTokenDTO.getToken());
        return Result.OK(imUserToken);
    }

    @Resource
    private TencentCmsUtils tencentCmsUtils;

    @PostMapping("/tencentSms")
    @ApiOperation(value = "腾讯云短信发送")
    public Result tencentSms(@RequestBody TelDTO telDTO) {
        String code = tencentCmsUtils.send(telDTO.getTel());
        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();
        // 根据手机号进行查询
        String phone = stringR.get(telDTO.getTel());
        // 如果手机号在redis中不存在的话才进行发送验证码操作
        if (StringUtils.isEmpty(phone)) {
            // 调用业务层接口 发送验证码
            String sendSmsFlag = tencentCmsUtils.send(telDTO.getTel());
            if (sendSmsFlag.equals("0")) {
                return Result.error525("短信发送频率限制！");
            } else {
                // 发送成功之后往redis中存入该手机号以及验证码 并设置超时时间 15 分钟
                stringR.set(telDTO.getTel(), code, 15, TimeUnit.MINUTES);
            }
            return Result.OK();
        } else {
            return Result.error("该手机号：" + telDTO.getTel() + " 剩余：" + redisTemplate.getExpire(telDTO.getTel()) + "秒后可再次进行发送！");
        }
    }

    @PostMapping("/tencentCode")
    @ApiOperation(value = "腾讯云短信检测验证码是否过期！")
    public Result redisCode(@RequestBody TelAndCodeDTO telAndCodeDTO) {
        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();
        // 根据手机号进行查询
        String redisCode = stringR.get(telAndCodeDTO.getTel());
        if (redisCode != null) {
            if (!redisCode.equals(telAndCodeDTO.getCode())) {
                return Result.error("验证码已过期，请重新获取！");
            }
        } else if(redisCode==null) {
            return Result.error("验证码已过期，请重新获取！");
        }
        return Result.OK(redisCode);
    }

    @PostMapping("/userCompany")
    @ApiOperation(value = "根据token更新用户信息")
    public Result userCompany(@RequestBody CompanyDTO companyDTO) {
        Integer checkUserToken = imUserMapper.checkToken(companyDTO.getToken());
        if (checkUserToken == 0) {
            return Result.error520("token不存在或已经过期了！");
        }
        ImUser imUserToken = imUserMapper.getToken(companyDTO.getToken());
        imUserToken.setRealName(companyDTO.getRealName());
        imUserToken.setTel(companyDTO.getTel());
        imUserToken.setCompanyPosition(companyDTO.getCompanyPosition());
        imUserToken.setCompanyName(companyDTO.getCompanyName());
        imUserToken.setCompanyNo(companyDTO.getCompanyNo());
        imUserToken.setIdentificationPhoto(companyDTO.getIdentificationPhoto());
        imUserToken.setToken(companyDTO.getToken());
        imUserMapper.updImUserCompany(imUserToken);
        ImUser imUserSelect = imUserMapper.getToken(companyDTO.getToken());
        return Result.OK(imUserSelect);
    }

    @ApiOperation(value = "上传文件，支持批量上传", notes = "form-data")
    @PostMapping("/upload-file")
    public Result<ArrayList<FileInfoDTO>> uploadChatTempFile(MultipartFile[] files) {
        String server = Tools.getRandom(configUtil.getStringListIgnoreBlank("file.server"), "");
        log.info(server + "server");
        ArrayList<FileInfoDTO> list = new ArrayList<>(files.length);
        for (MultipartFile file : files) {
            FileInfo fileInfo = fileStorageService.of(file)
                    .setObjectId(3)
                    .setObjectType(Constant.FileStorage.IM_USER_UPLOAD.type)
                    .setPath(Constant.FileStorage.IM_USER_UPLOAD.path)
                    .upload();
            list.add(new FileInfoDTO(fileInfo, server));
        }
        return Result.OK(list);
    }
}
