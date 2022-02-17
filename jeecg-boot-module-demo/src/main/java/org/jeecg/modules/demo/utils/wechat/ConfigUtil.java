package org.jeecg.modules.demo.utils.wechat;
import org.jeecg.modules.demo.cms.manager.sysconfig.ISysConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author henry(zhanghai)
 * @date 2022-02-09 12:42
 */
@Component
public class ConfigUtil {
    @Autowired
    private ISysConfigUtil configUtil;

    /**
     * 获取指定数据
     */
    public String get(String cmsKey) {
        return configUtil.get(cmsKey);
    }

    /**
     * 批量获取
     */
    public Map<String, String> get(Collection<? extends String> coll) {
        return configUtil.get(coll);
    }

    public int getInt(String cmsKey) {
        return Integer.parseInt(get(cmsKey));
    }

    public long getLong(String cmsKey) {
        return Long.parseLong(get(cmsKey));
    }

    public BigDecimal getBigDecimal(String cmsKey) {
        return new BigDecimal(get(cmsKey));
    }

    public boolean getBoolean(String cmsKey) {
        return StringUtils.parseBoolean(get(cmsKey));
    }

    public List<String> getStringListIgnoreBlank(String cmsKey) {
        return StringUtils.parseStringListIgnoreBlank(get(cmsKey));
    }
}
