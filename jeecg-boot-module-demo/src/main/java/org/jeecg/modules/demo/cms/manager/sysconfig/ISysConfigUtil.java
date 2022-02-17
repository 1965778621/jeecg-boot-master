package org.jeecg.modules.demo.cms.manager.sysconfig;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * 获取系统配置数据
 */
@Component
public interface ISysConfigUtil {
    boolean set(String cmsKey,String val);

    /**
     * 获取指定数据
     */
    String get(String cmsKey);

    /**
     * 批量获取
     */
    Map<String, String> get(Collection<? extends String> coll);

    default int getInt(String cmsKey) {
        return Integer.parseInt(get(cmsKey));
    }

    default BigDecimal getBigDecimal(String cmsKey) {
        return new BigDecimal(get(cmsKey));
    }
}
