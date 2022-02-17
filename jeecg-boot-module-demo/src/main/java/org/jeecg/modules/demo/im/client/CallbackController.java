package org.jeecg.modules.demo.im.client;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.demo.im.manager.entity.ImUser;
import org.jeecg.modules.demo.im.manager.mapper.ImUserMapper;
import org.jeecg.modules.demo.utils.wechat.HttpClientUtil;
import org.jeecg.modules.demo.utils.wechat.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import com.aliyuncs.utils.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author henry(zhanghai)
 * @date 2022-02-08 23:11
 */
@Controller
@CrossOrigin
@Api(tags = "公众号回调接口，需要先授权登录才能访问正式版本")
@Slf4j
public class CallbackController {
    @Value("${tencent.appid}")
    private String appid;
    @Value("${tencent.appsecret}")
    private String appsecret;
    @Resource
    private ImUserMapper imUserMapper;

    @RequestMapping("/callback")
    @ApiOperation(value = "公众号回调请求，先授权登录才能执行")
    public String callback(String code, HttpServletResponse response) throws IOException {
        // 第二步：通过code换取网页授权access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid +
                "&secret=" + appsecret +
                "&code=" + code +
                "&grant_type=authorization_code";
        /**
         * {"access_token":"53_RqDALywd1xGorEvxcaqa6x5RJcVLPI687HLr4JWQqUdCRDyrOm5V_r3UTx0tZpDinFOiScn_px-V-9HnPeM52h2J_-11aaduxnbfeAxWzZo",
         * "refresh_token":"53_pB_bOv8xpq5Bn5ZjZ36MpzkzLWviAeN3_Y1E0p87TrTU8NbG6r7-vKs0SBy_RY0feFsPvXkd5uA1h-3c_fLFpXKtS0zCsSXpWp30cJCpLT4",
         * "openid":"orLa25kKWBuFF2tfO6fBmRNtBvcg","scope":"snsapi_userinfo","expires_in":7200}
         */
        JSONObject jsonObject = HttpClientUtil.doGet(url);
        String openidMphelper = jsonObject.getString("openid");
        String access_Token = jsonObject.getString("access_token");
        url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_Token +
                "&openid=" + openidMphelper +
                "&lang=zh_CN";
        JSONObject userInfoJson = HttpClientUtil.doGet(url);
        String nickNameMphelper = userInfoJson.getString("nickname");
        String headimgurl = userInfoJson.getString("headimgurl");
        /**
         * {"country":"","province":"","city":"",
         * "openid":"orLa25kKWBuFF2tfO6fBmRNtBvcg",
         * "sex":0,"nickname":"张海",
         * "headimgurl":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKe2HwTYcQ0h0vX9sR9cC2pzuib5GqOjCRa9dPF5CNvIQQWvkyZ4S3RTAlYSmPVQF8GjQ2TTYz3wuw/132",
         * "language":"","privilege":[]}j
         */
        Integer checkOpenid = imUserMapper.checkOpenid(openidMphelper);
        ImUser imUser = new ImUser();
        imUser.setOpenidMphelper(openidMphelper);
        IdWorker idWorker = new IdWorker(1, 1);
        long l = idWorker.nextId();
        String tokenRandom = String.valueOf(UUID.randomUUID()) + l;
        Date month = DateUtil.nextWeek();
        log.info(l + "l");
        // 插入数据
        if (checkOpenid == 0) {
            imUser.setNickNameMphelper(nickNameMphelper);
            imUser.setHeadimgurl(headimgurl);
            imUser.setCreateTime(new Date());
            imUser.setToken(tokenRandom);
            imUser.setTokenTime(month);
            if (!StringUtils.isEmpty(openidMphelper)) {
                imUserMapper.insert(imUser);
            }
        } else {
            ImUser imUserSelect = imUserMapper.getImUser(openidMphelper);
            // 更新数据
            imUser.setNickNameMphelper(nickNameMphelper);
            imUser.setHeadimgurl(headimgurl);
            imUserMapper.updImUser(imUser);
            String tokenUpd = imUserSelect.getToken();
            if (tokenUpd == null || tokenUpd.equals("")) {
                imUser.setToken(tokenRandom);
                imUser.setTokenTime(month);
                imUser.setOpenidMphelper(openidMphelper);
                imUserMapper.updToken(imUser);
            }
        }
        ImUser imUserSelect = imUserMapper.getImUser(openidMphelper);
        log.info(imUserSelect + "imUserSelect");
        return "redirect:https://web.ysfgpx.cn?token=" + imUserSelect.getToken();
    }
}
