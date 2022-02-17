package org.jeecg.modules.demo.utils.wechat;

import java.util.HashMap;
import java.util.Map;

/**
 * @author henry(zhanghai)
 * @date 2022-02-10 13:25
 */
public class CamelMap<V> extends HashMap<String, V> {

    public CamelMap() {
        super();
    }

    public CamelMap(String key, V val) {
        super();
        put(key,val);
    }
    public CamelMap(Map.Entry<String, V> entry) {
        super();
        put(entry.getKey(),entry.getValue());
    }
    public CamelMap(Map<? extends String, ? extends V> m) {
        super(m.size());
        m.forEach(this::put);
    }

    @Override
    public V put(String key,V value) {
        return super.put(parseKey(key),value);
    }

    public CamelMap<V> add(String key,V value) {
        super.put(parseKey(key),value);
        return this;
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> m) {
        m.forEach(this::put);
    }

    public CamelMap<V> addAll(Map<? extends String, ? extends V> m) {
        m.forEach(this::put);
        return this;
    }

    @Override
    public V get(Object key) {
        if (key instanceof String) {
            return super.get(parseKey((String) key));
        }
        return null;
    }


    protected String parseKey(String key) {
        return key.contains("_") ? StringUtils.underlineToCamel(key) : key;
    }
}
