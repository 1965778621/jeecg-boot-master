package org.jeecg.modules.demo.utils.wechat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

import java.io.File;


/**
 * @author henry(zhanghai)
 * @date 2022-02-11 23:34
 */
@Component
@Slf4j
public class AudioTimeUtils {
    public static void main(String[] args) {
        AudioTimeUtils r = new AudioTimeUtils();
        //
        // 前端展示使用
        String audioTimeWeb = r.AudioTimeWeb("F:\\2.mp4");
        // 后台展示使用 （都需要存入数据库）
        long api1 = r.AudioTimeApi("F:\\2.mp4");
        long api2 = r.AudioTimeApi("F:\\1.mp4");
        long audioTimeApi = api1 + api2;
        System.out.println("前端显示时长：" + audioTimeWeb);
        System.out.println("后台计算时长：" + audioTimeApi);

    }

    /**
     * 传入音频链接
     * 获取视频时长 后台使用的（后台使用）
     *
     * @param FileUrl
     * @return
     */
    public static long AudioTimeApi(String FileUrl) {
        File source = new File(FileUrl);
        String length = "";
        MultimediaObject instance = new MultimediaObject(source);
        MultimediaInfo result = null;
        try {
            result = instance.getInfo();
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        long ls = result.getDuration() / 1000;
        return ls;
    }

    /**
     * 传入音频链接
     * 获取音频时长 前端展示时长渲染的（前端时长的）
     *
     * @param FileUrl
     * @return
     */
    public static String AudioTimeWeb(String FileUrl) {
        File source = new File(FileUrl);
        String length = "";
        try {
            MultimediaObject instance = new MultimediaObject(source);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }
}
