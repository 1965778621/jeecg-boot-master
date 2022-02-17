package org.jeecg.modules.demo.cms.client;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.cms.manager.entity.CmsBanner;
import org.jeecg.modules.demo.cms.manager.mapper.CmsBannerMapper;
import org.jeecg.modules.demo.utils.wechat.Constant;
import org.jeecg.modules.demo.utils.wechat.Tools;
import org.springframework.stereotype.Service;

/**
 * \* @author: ZH
 * \* Date:    2022/2/15/015
 * \* Time:    上午 1:14
 * \* Description:
 * \
 */
@Service
public class CmsBannerService extends ServiceImpl<CmsBannerMapper, CmsBanner> {
    /**
     * 获取轮播图列表
     *
     * @param current      当前页
     * @param size         每页数量
     */
    public IPage<CmsBanner> list(long current, long size) {
        return page(Tools.page(current, size), new QueryWrapper<CmsBanner>()
                .eq(CmsBanner.COL_DEL_FLAG, Constant.DELFLAG_NORMAL)
        );
    }
}
