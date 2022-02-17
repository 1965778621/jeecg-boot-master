package org.jeecg.modules.demo.cms.manager.sysconfig;

import lombok.Getter;

/**
 * 系统配置加载时的事件
 */
@Getter
public class SysConfigLoadEvent extends CmsConfigEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SysConfigLoadEvent(Object source,String cmsKey,String val) {
        super(source,cmsKey,val);
    }
}
