package org.jeecg.modules.demo.cms.client;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.cms.manager.entity.CmsBanner;
import org.jeecg.modules.demo.cms.manager.entity.TestPaperConfig;
import org.jeecg.modules.demo.cms.manager.mapper.CmsBannerMapper;
import org.jeecg.modules.demo.cms.manager.mapper.TestPaperConfigMapper;
import org.jeecg.modules.demo.utils.wechat.Constant;
import org.jeecg.modules.demo.utils.wechat.Tools;
import org.springframework.stereotype.Service;

/**
 * \* @author: ZH
 * \* Date:    2022/2/18/018
 * \* Time:    下午 3:03
 * \* Description:
 * \
 */
@Service
public class CmsTestPaperConfigService extends ServiceImpl<TestPaperConfigMapper, TestPaperConfig> {
    /**
     * 获取考试配置列表
     *
     * @param current      当前页
     * @param size         每页数量
     */
    public IPage<TestPaperConfig> list(long current, long size) {
        return page(Tools.page(current, size), new QueryWrapper<TestPaperConfig>());
//           .eq(TestPaperConfig.COL_DEL_FLAG, Constant.DELFLAG_NORMAL)
    }

}
