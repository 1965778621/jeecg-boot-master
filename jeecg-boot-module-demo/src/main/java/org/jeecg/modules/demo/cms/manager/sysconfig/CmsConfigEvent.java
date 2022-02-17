package org.jeecg.modules.demo.cms.manager.sysconfig;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * 系统配置的事件
 */
@Getter
@ToString
public class CmsConfigEvent extends ApplicationEvent {

    private final String cmsKey;
    private final String val;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public CmsConfigEvent(Object source, String cmsKey, String val) {
        super(source);
        this.cmsKey = cmsKey;
        this.val = val;
    }
}
