package org.jeecg.modules.demo.utils.wechat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author henry(zhanghai)
 * @date 2022-02-10 11:26
 * 腾讯云发送短信
 */
@Component
@Slf4j
public class TencentCmsUtils {
    /**
     * 账号相关
     */
    @Value("${account.secret-id}")
    private Integer SECRET_ID;

    @Value("${account.secret-key}")
    private String SECRET_KEY;

    @Value("${account.template-id}")
    private Integer TEMPLATE_ID;

    @Value("${account.sign_name}")
    private String SIGN_NAME;

    public String send(String tel) {
        int appid = SECRET_ID;
        String PhoneNumber = tel;
        // 短信应用SDK AppKey
        String appkey = SECRET_KEY;
        // 需要发送短信的手机号码
        String[] phoneNumbers = {PhoneNumber};//多个号码用,隔开
        // 短信模板ID，需要在短信应用中申请
        // NOTE: 这里的模板ID`7839`只是一个示例，
        // 真实的模板ID需要在短信控制台中申请
        int templateId = TEMPLATE_ID;
        String smsSign = SIGN_NAME;
        String number = null;
        try {
            number = (long) (Math.random() * 1000000) + "";//生成六位验证码
            String[] params = {number};
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            SmsMultiSenderResult smsMultiSenderResult = msender.sendWithParam("86", phoneNumbers,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            log.info(msender+"msender");
            log.info(smsMultiSenderResult+"smsMultiSenderResult");
            if (smsMultiSenderResult.result == 0) {
                return number;
            }
        } catch (Exception e) {

        }
        return null;
    }
}
