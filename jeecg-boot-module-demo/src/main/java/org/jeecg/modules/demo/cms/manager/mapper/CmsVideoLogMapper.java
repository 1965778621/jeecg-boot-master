package org.jeecg.modules.demo.cms.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.cms.manager.entity.CmsVideoLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 视频播放记录
 * @Author: jeecg-boot
 * @Date:   2022-02-10
 * @Version: V1.0
 */
public interface CmsVideoLogMapper extends BaseMapper<CmsVideoLog> {
    /**
     * 获取观看记录
     * @param userId 用户id
     * @param videoId 视频id
     * @return
     */
    CmsVideoLog getVideoLogByUserIdAndVideoId(Integer userId,String videoId);

    /**
     * 获取用户观看记录
     * @param userId
     * @return
     */
    List<CmsVideoLog> getVideoLogByUserId(Integer userId);

}
