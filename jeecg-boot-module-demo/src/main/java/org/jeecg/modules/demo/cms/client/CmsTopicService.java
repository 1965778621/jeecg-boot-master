package org.jeecg.modules.demo.cms.client;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.cms.manager.entity.CmsTopic;
import org.jeecg.modules.demo.cms.manager.mapper.CmsTopicMapper;
import org.jeecg.modules.demo.utils.wechat.Constant;
import org.jeecg.modules.demo.utils.wechat.Tools;
import org.springframework.stereotype.Service;

/**
 * \* @author: ZH
 * \* Date:    2022/2/16/016
 * \* Time:    下午 3:02
 * \* Description:
 * \
 */
@Service
public class CmsTopicService extends ServiceImpl<CmsTopicMapper, CmsTopic> {
    /**
     * 获取题目列表
     *
     * @param videoCode 视频id
     * @param current   当前页
     * @param size      每页数量
     */
    public IPage<CmsTopic> list(long current, long size, String videoCode) {
        return page(Tools.page(current, size),
                new QueryWrapper<CmsTopic>()
                        .eq(CmsTopic.COL_DEL_FLAG, Constant.DELFLAG_NORMAL)
                        .eq(CmsTopic.COL_VIDEO_CODE, videoCode)
        );
    }
}
