package org.jeecg.modules.demo.utils.wechat;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author henry(zhanghai)
 * @date 2022-02-08 23:14
 */
public class HttpClientUtil {
    /**
     * 发起一个GET请求, 返回数据是以JSON格式返回
     * @param url
     * @return
     * @throws IOException
     */
    public static JSONObject doGet(String url) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = HttpClients.createDefault();


        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSONObject.parseObject(result);
        }

        httpGet.releaseConnection();
        return jsonObject;
    }
}
