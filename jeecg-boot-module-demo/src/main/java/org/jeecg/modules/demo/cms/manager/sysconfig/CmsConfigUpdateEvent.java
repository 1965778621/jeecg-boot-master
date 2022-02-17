package org.jeecg.modules.demo.cms.manager.sysconfig;

import lombok.Getter;

/**
 * 系统配置更新时的事件
 */
@Getter
public class CmsConfigUpdateEvent extends CmsConfigEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public CmsConfigUpdateEvent(Object source, String cmsKey, String val) {
        super(source,cmsKey,val);
    }
}
