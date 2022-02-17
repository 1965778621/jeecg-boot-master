package org.jeecg.modules.demo.im.client;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.demo.utils.wechat.ConfigUtil;
import org.jeecg.modules.demo.utils.wechat.WeChatTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author henry(zhanghai)
 * @date 2022-02-08 23:13
 */
@RestController
@RequestMapping("/im/client/tencentToken")
@Api(tags = "公众号token检测接口")
@CrossOrigin // 跨域支持
@Slf4j
public class TencentTokenController extends HttpServlet {
    /**
     * 配置微信公众号基本url，使其获取到token
     */
    @ResponseBody
    @RequestMapping("/token")
    @ApiOperation(value = "token验证")
    @Transactional
    public void weixininter(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //微信获取解析信息
        String str = WeChatTokenUtils.check_Url(request);
        log.info(str+"token验证");
        PrintWriter out = response.getWriter();
        //输出
        out.print(str);
        //刷新
        out.flush();
        //关闭流
        out.close();
    }

    @Autowired
    private ConfigUtil configUtil;

    @ApiOperation(value = "获取文件服务器地址信息")
    @GetMapping("/fileServerList")
    public Result<List<String>> all() {
        log.info(configUtil.getStringListIgnoreBlank("file.server")+"11111111111");
        return Result.OK(configUtil.getStringListIgnoreBlank("file.server"));
    }
}
