package org.jeecg.modules.demo.utils.wechat;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author henry(zhanghai)
 * @date 2022-02-09 12:44
 */
@Slf4j
public class Tools {
    /**
     * 按照参数从前往后进行判断，返回第一个不为 null 的参数
     */
    @SafeVarargs
    public static <T> T getNotNull(T... args) {
        for (T t : args)
            if (t != null)
                return t;
        throw new NullPointerException();
    }

    /**
     * 获取客户端IP
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (org.apache.commons.lang.StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (org.apache.commons.lang.StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (org.apache.commons.lang.StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (org.apache.commons.lang.StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (org.apache.commons.lang.StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            return "127.0.0.1";
        }

        //使用代理，则获取第一个IP地址，不截取则是经过的所有的服务器的地址
        if (StringUtils.notEmpty(ip) && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }

        return ip;
    }

    /**
     * 获取客户端IP
     */
    public static String getClientIp() {
        return getClientIp(getRequest());
    }

    /**
     * 将ip地址转换成点分十进制，192.168.1.1 转换成 192.168.001.001
     */
    public static String ipPad(String ip) {
        List<String> list = Arrays.stream(ip.split("\\.")).map(s -> StringUtils.padPre(s, 3, '0')).collect(Collectors.toList());
        return StringUtils.join(".", list);
    }

    /**
     * 判断请求是否是Ajax
     */
    public static boolean isAJAX(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        return accept != null && accept.contains("application/json") || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest"));
    }

    /**
     * 判断请求是否是Ajax，自动获取当前 request 对象
     */
    public static boolean isAJAX() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            return isAJAX(request);
        }
        return false;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            return requestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 创建指定类型的对象，必须要有一个无参构造方法，并尽量把 obj 的值拷贝给它，最后返回这个对象，失败时返回null
     */
    public static <T> T copyProperties(Object obj, Class<T> clazz) {
        if (obj == null) return null;
        T t = null;
        try {
            t = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(t, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * Bean 转 Map，这里暂时有一些问题，对于代理对象或有父类的对象，可能会有属性获取不到或报错，等日后修复
     */
    public static Map<String, Object> objectToMap(Object obj) {
        HashMap<String, Object> res = new HashMap<>();   //创建新的容器用来保存过滤后的值
        try {
            Class<?> clazz = obj.getClass();    //获得被过滤的对象的类型
            if (clazz.getTypeName().contains("$")) {  //通过类名判断是否为代理对象
                Type genericSuperclass = clazz.getGenericSuperclass();
                clazz = Class.forName(genericSuperclass.getTypeName()); //获得被代理对象的class
            }
            Field[] fields = clazz.getDeclaredFields(); //获得被过滤对象的所有成员变量
            for (Field field : fields) {
                try {
                    String key = field.getName();   //获得属性名称
                    Object value = new PropertyDescriptor(key, clazz).getReadMethod().invoke(obj);   //获得属性值
                    res.put(key, value);    //保存当前属性和值
                } catch (Exception ignored) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * 将对象以json的形式写到响应流中
     */
    public static void writerResponse(HttpServletResponse response, Object obj) {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取指定名称的 cookie，这里还未使用 EscapeUtil.unescape() 进行处理，你要自行处理
     * 通过 core.js 存储的 cookie 都是经过 escape() 函数处理的，所以读取 value 时要用 unescape() 进行处理
     */
    public static Cookie getCookie(String name) {
        if (StringUtils.isBlank(name)) return null;
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }

    /**
     * 获取指定名称的 cookie 的 value，这里已经用 EscapeUtil.unescape() 进行处理了
     * 通过 core.js 存储的 cookie 都是经过 escape() 函数处理的，所有读取 value 时要用 unescape() 进行处理
     */
    public static String getCookieValue(String name) {
        Cookie cookie = getCookie(name);
        if (cookie != null) {
            return EscapeUtil.unescape(cookie.getValue());
        }
        return null;
    }


    /**
     * 关闭 Closeable
     */
    public static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 随机概率 0-100，小于等于0永远 false，大于等于100永远 true
     */
    public static boolean randomPercent(int percent) {
        if (percent <= 0) return false;
        if (percent >= 100) return true;
        return new Random().nextInt(99) < percent;
    }

    /**
     * 转换分页对象的类型
     */
    public static <T, R> IPage<R> parsePage(IPage<T> page, Function<? super T, ? extends R> mapper) {
        return new Page<R>()
                .setCurrent(page.getCurrent())
                .setSize(page.getSize())
                .setPages(page.getPages())
                .setTotal(page.getTotal())
                .setRecords(page.getRecords().stream().map(mapper).collect(Collectors.toList()));
    }

    /**
     * Iterator（迭代器） 转 list
     */
    public static <T> List<T> toList(Iterator<T> iterator) {
        List<T> list = new ArrayList<>();
        if (iterator == null) {
            return list;
        }
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    /**
     * Enumeration 转 list
     */
    public static <T> List<T> toList(Enumeration<T> enumeration) {
        List<T> list = new ArrayList<>();
        if (enumeration == null) {
            return list;
        }
        while (enumeration.hasMoreElements()) {
            list.add(enumeration.nextElement());
        }
        return list;
    }

    /**
     * List 转 Map
     */
    public static <T extends Map<K, V>, K, V> HashMap<K, V> listToMap(List<T> list) {
        return listToMap(list, new HashMap<>());
    }

    /**
     * List 转 Map
     */
    public static <T extends Map<K, V>, K, V, M extends Map<K, V>> M listToMap(List<T> list, M map) {
        list.forEach(map::putAll);
        return map;
    }

    /**
     * Map 转 List
     */
    public static <K, V> ArrayList<HashMap<K, V>> mapToList(Map<K, V> map) {
        return mapToList(map, HashMap::new);
    }

    /**
     * Map 转 List
     */
    public static <K, V, M extends Map<K, V>> ArrayList<M> mapToList(Map<K, V> map, Supplier<M> supplier) {
        ArrayList<M> list = new ArrayList<>(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            M m = supplier.get();
            m.put(entry.getKey(), entry.getValue());
            list.add(m);
        }
        return list;
    }

    /**
     * 随机获取 list 中一个元素，如果 list 为 null 或空则返回默认值
     */
    public static <T> T getRandom(List<T> list, T def) {
        if (list == null || list.size() == 0) return def;
        return RandomUtil.randomEle(list);
    }

    /**
     * 按照每个元素的权重随机获取并返回其中一个元素下标，失败返回-1，仅支持整数，不支持浮点数
     */
    public static int getRandomByWeight(List<? extends Number> list) {
        if (CollUtil.isEmpty(list)) return -1;
        int sum = 0;
        List<Long> coll = list.stream().map(Number::longValue).collect(Collectors.toList());
        for (Long item : coll) {
            if (item == null || item == 0) continue;
            sum += item;
        }
        if (sum == 0) return -1;

        long point = RandomUtil.randomLong(sum) + 1;

        //判断中了哪个
        long temp = 0;
        for (int i = 0; i < coll.size(); i++) {
            Long item = coll.get(i);
            if (item == null || item == 0) continue;
            temp += item;
            if (point <= temp) return i;
        }
        return -1;
    }

    /**
     * 对 Map 的 Value 进行类型转换
     */
    public static <K, V, O> Map<K, V> mapValue(Map<K, O> map, Function<O, V> function) {
        Map<K, V> newMap = new HashMap<>(map.size());
        for (Map.Entry<K, O> entry : map.entrySet()) {
            newMap.put(entry.getKey(), function.apply(entry.getValue()));
        }
        return newMap;
    }

//    public static String getFilePath(String path){
//        if(FileUtil.exist(path)) return path;
//        Tools.class.getClassLoader().getResource().
//    }

    /**
     * 构造分页对象，页码为负数的情况下查询全部
     */
    public static <T> Page<T> page(long current) {
        return current < 0 ? new Page<>(1, 999999999, 999999999, false) : new Page<>(current, 10);
    }

    /**
     * 构造分页对象，页码为负数的情况下查询全部
     */
    public static <T> Page<T> page(long current, long size) {
        return current < 0 ? new Page<>(1, 999999999, 999999999, false) : new Page<>(current, size);
    }

    /**
     * 安全的获取 map 中的值，如果 map 为 null 则直接返回 null
     */
    public static <K, V> V safeGet(Map<K, V> map, K key) {
        return map == null ? null : map.get(key);
    }

    /**
     * 安全的获取 map 中的值，如果 map 为 null 或者 获取到的值为 null 将直接返回 v
     */
    public static <K, V> V safeGet(Map<K, V> map, K key, V v) {
        V res = map == null ? null : map.get(key);
        return res == null ? v : res;
    }

    /**
     * 安全的获取 list 中的值，如果 list 为 null 则直接返回 null
     */
    public static <V> V safeGet(List<V> list, int index) {
        if (list == null || list.size() <= index) return null;
        return list.get(index);
    }

    /**
     * 安全的获取 list 中的值，如果 list 为 null 或者 获取到的值为 null 将直接返回 v
     */
    public static <V> V safeGet(List<V> list, int index, V v) {
        V res = safeGet(list, index);
        return res == null ? v : res;
    }

    /**
     * 按照回调方法的值进行分组，例如 [1,18,3,4,5,5,5,6,3,4] 分组后 [[1], [18], [3, 3], [4, 4], [5, 5, 5], [6]]
     */
    public static <T> Collection<? extends List<T>> group(Iterable<T> iterable, Function<? super T, ?> mapper) {
        HashMap<Object, List<T>> map = new HashMap<>();
        for (T item : iterable) {
            Object key = mapper.apply(item);
            List<T> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(item);
        }
        return map.values();
    }

    private static double EARTH_RADIUS = 6378.137;//地球半径（单位：KM）

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两个点的经纬度计算距离(单位：米)
     * @param lng1 第一个点的经度
     * @param lat1 第一个点的纬度
     * @param lng2 第二个点的经度
     * @param lat2 第二个点的纬度
     * @return 距离（单位：米）
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s*1000;
        return s;
    }

}
