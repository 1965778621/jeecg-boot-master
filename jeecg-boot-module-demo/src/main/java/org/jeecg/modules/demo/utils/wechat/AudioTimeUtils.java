package org.jeecg.modules.demo.utils.wechat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

import java.io.File;
import java.net.URL;


/**
 * @author henry(zhanghai)
 * @date 2022-02-11 23:34
 */
@Component
@Slf4j
public class AudioTimeUtils {
    public static void main(String[] args) {
        AudioTimeUtils r = new AudioTimeUtils();
        // 前端展示使用
        String audioTimeWeb = null;
        // 后台展示
        Long audioTimeApi = 0L;
        try {
            audioTimeWeb = r.webVideoTimeStr("https://fileurl.yudoule.com/mda-mmg1kfekeihyp0vq.mp4");
            audioTimeApi = r.apiVideoTime("https://fileurl.yudoule.com/mda-mmg1kfekeihyp0vq.mp4");
            log.info(audioTimeWeb + "audioTimeWeb");
            log.info(audioTimeApi + "audioTimeApi");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取外链接视频单位是s的视频
     *
     * @param fileUrl
     * @return
     * @throws Exception
     */
    public static Long apiVideoTime(String fileUrl) throws Exception {
        URL URL = new URL(fileUrl);
        long ls = 0L;
        try {
            MultimediaObject instance = new MultimediaObject(URL);
            MultimediaInfo result = instance.getInfo();
            ls = result.getDuration() / 1000;
            log.info(ls + "ls");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    /**
     * 获取外链接视频时间格式的视频
     *
     * @param fileUrl
     * @return
     * @throws Exception
     */
    public static String webVideoTimeStr(String fileUrl) throws Exception {
        URL URL = new URL(fileUrl);
        String length = "";
        try {
            MultimediaObject instance = new MultimediaObject(URL);
            MultimediaInfo result = instance.getInfo();
            long ls = result.getDuration() / 1000;
            log.info(ls + "ls");
            Integer hour = (int) (ls / 3600);
            Integer minute = (int) (ls % 3600) / 60;
            Integer second = (int) (ls - hour * 3600 - minute * 60);
            String hr = hour.toString();
            String mi = minute.toString();
            String se = second.toString();
            if (hr.length() < 2) {
                hr = "0" + hr;
            }
            if (mi.length() < 2) {
                mi = "0" + mi;
            }
            if (se.length() < 2) {
                se = "0" + se;
            }
            if (hr.equals("00")) {
                length = mi + ":" + se;
            } else if (!hr.equals("00")) {
                length = hr + ":" + mi + ":" + se;
            }
            log.info(length + "length");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }
}
