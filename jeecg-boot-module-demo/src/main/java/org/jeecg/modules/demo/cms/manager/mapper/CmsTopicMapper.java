package org.jeecg.modules.demo.cms.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.cms.manager.entity.CmsTopic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 考试题目表
 * @Author: jeecg-boot
 * @Date:   2022-02-15
 * @Version: V1.0
 */
public interface CmsTopicMapper extends BaseMapper<CmsTopic> {
    /**
     * 考试题目类型
//     * @param videoId 视频id
     * @param topicType 题目类型
     * @return
     * //    Integer getCount(String videoId,String topicType);
     */
    Integer getCount(String topicType);


}
