package org.jeecg.modules.demo.cms.manager.service.impl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.demo.cms.manager.entity.CmsConfig;
import org.jeecg.modules.demo.cms.manager.mapper.CmsConfigMapper;
import org.jeecg.modules.demo.cms.manager.service.ICmsConfigService;
import org.jeecg.modules.demo.cms.manager.sysconfig.CmsConfigEvent;
import org.jeecg.modules.demo.cms.manager.sysconfig.CmsConfigUpdateEvent;
import org.jeecg.modules.demo.cms.manager.sysconfig.ISysConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 参数配置
 * @Author: jeecg-boot
 * @Date:   2022-02-09
 * @Version: V1.0
 */
@Service
@Slf4j
public class CmsConfigServiceImpl extends ServiceImpl<CmsConfigMapper, CmsConfig> implements ICmsConfigService, ISysConfigUtil {
    private final HashMap<String,String> map = new HashMap<>();
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     * 当前 bean 刷新事件执行时，向系统广播所有配置
     */
    @EventListener
    public void onContextRefreshedEventListener(ContextRefreshedEvent event) {
        for (CmsConfig config : list()) {
            eventPublisher.publishEvent(new CmsConfigEvent(this,config.getCmsKey(),config.getValue()));
        }
    }

    @Override
    public boolean set(String cmsKey,String val) {
        log.info(cmsKey+"file.server");
        CmsConfig config = new CmsConfig();
        config.setCmsKey(cmsKey);
        config.setValue(val);
        boolean b = saveOrUpdate(config);
        if(b){
            map.put(cmsKey,val);
            eventPublisher.publishEvent(new CmsConfigUpdateEvent(this,cmsKey,val));
        }
        return b;
    }

    @Override
    public boolean updateById(CmsConfig entity) {
        boolean b = super.updateById(entity);
        if(b){
            CmsConfig config = getById(entity.getId());
            map.put(config.getCmsKey(),config.getValue());
            eventPublisher.publishEvent(new CmsConfigUpdateEvent(this,config.getCmsKey(),config.getValue()));
        }
        return b;
    }

    @Override
    public String get(String cmsKey) {
        if(map.containsKey(cmsKey)){
            return map.get(cmsKey);
        }
        CmsConfig config = getById(cmsKey);
        String val = config != null ? config.getValue() : null;
        map.put(cmsKey,val);
        return val;
    }

    @Override
    public Map<String, String> get(Collection<? extends String> coll) {
        HashMap<String, String> map = new HashMap<>();
        for (CmsConfig config : listByIds(coll)) {
            map.put(config.getCmsKey(),config.getValue());
        }
        return map;
    }


}
