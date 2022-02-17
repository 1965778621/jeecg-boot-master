package org.jeecg.modules.demo.cms.client;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.cms.manager.entity.CmsVideoLog;
import org.jeecg.modules.demo.cms.manager.mapper.CmsVideoLogMapper;
import org.jeecg.modules.demo.utils.wechat.Constant;
import org.jeecg.modules.demo.utils.wechat.Tools;
import org.springframework.stereotype.Service;

/**
 * @author henry(zhanghai)
 * @date 2022-02-15 19:41
 */
@Service
public class ClientVideoLogService extends ServiceImpl<CmsVideoLogMapper, CmsVideoLog> {
    /**
     * 获取视频列表
     *
     * @param current      当前页
     * @param size         每页数量
     */
    public IPage<CmsVideoLog> listLog(long current, long size,Integer userId) {
        return page(Tools.page(current, size), new QueryWrapper<CmsVideoLog>()
                .eq(userId != null, CmsVideoLog.USER_ID, userId)
                .eq(CmsVideoLog.COL_DEL_FLAG, Constant.DELFLAG_NORMAL)
        );
    }
}
