package org.jeecg.modules.demo.cms.client;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.cms.manager.entity.CmsVideo;
import org.jeecg.modules.demo.cms.manager.mapper.CmsVideoMapper;;
import org.jeecg.modules.demo.utils.wechat.Constant;
import org.jeecg.modules.demo.utils.wechat.Tools;
import org.springframework.stereotype.Service;

/**
 * @author henry(zhanghai)
 * @date 2022-02-10 13:27
 */
@Service
public class CmsVideoService extends ServiceImpl<CmsVideoMapper, CmsVideo> {

    /**
     * 获取视频列表
     *
     * @param current      当前页
     * @param size         每页数量
     */
    public IPage<CmsVideo> list(long current, long size) {
        return page(Tools.page(current, size), new QueryWrapper<CmsVideo>()
                .eq(CmsVideo.COL_DEL_FLAG, Constant.DELFLAG_NORMAL)
        );
    }
}
