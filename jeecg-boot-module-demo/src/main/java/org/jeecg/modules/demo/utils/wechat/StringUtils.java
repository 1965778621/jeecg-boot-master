package org.jeecg.modules.demo.utils.wechat;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import org.apache.catalina.util.ParameterMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author henry(zhanghai)
 * @date 2022-02-09 12:44
 */
public class StringUtils extends StrUtil {

    /**
     * 不是 null 或空字符串
     */
    public static boolean notEmpty(String str) {
        return str != null && str.length() > 0;
    }

    /**
     * 是 null 或空字符串
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 不是 null 或 trim 后空字符串
     */
    public static boolean notBlank(String str) {
        return str != null && str.trim().length() > 0;
    }

    /**
     * 是 null 或 trim 后空字符串
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 按照参数从前往后进行判断，返回第一个不为 null 且不为空字符串的参数
     */
    public static String getNotEmpty(String... args) {
        for (String str : args)
            if (StringUtils.notEmpty(str))
                return str;
        return null;
    }

    /**
     * 按照参数从前往后进行判断，返回第一个不为 null 且 trim 后不为空字符串的参数
     */
    public static String getNotBlank(String... args) {
        for (String str : args)
            if (notBlank(str))
                return str;
        return null;
    }

    /**
     * 将String转换成int 失败则返回默认值
     */
    public static int parseInt(String s, int n) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return n;
        }
    }

    /**
     * String 转 BigDecimalList，忽略其中不能转换的部分，
     */
    public static List<BigDecimal> parseBigDecimalList(String str, String regex) {
        Pattern pattern = Pattern.compile("^[-+]?[0-9]+(\\.[0-9]+)?$");
        return Arrays.stream(str.split(regex)).map(String::trim)
                .filter(v -> pattern.matcher(v).matches())
                .map(BigDecimal::new).collect(Collectors.toList());
    }

    /**
     * String 转 IntegerList，默认使用','分隔，忽略其中不能转换的部分，
     */
    public static List<Integer> parseIntList(String str) {
        return parseIntList(str, ",");
    }

    /**
     * String 转 IntegerList，忽略其中不能转换的部分，
     */
    public static List<Integer> parseIntList(String str, String regex) {
        Pattern pattern = Pattern.compile("^[-+]?[0-9]+([0-9]+)?$");
        return Arrays.stream(str.split(regex)).map(String::trim)
                .filter(v -> pattern.matcher(v).matches())
                .map(Integer::parseInt).collect(Collectors.toList());
    }

    /**
     * String 转 StringList，自动对每个元素删首尾空
     */
    public static List<String> parseStringList(String str) {
        return parseStringList(str, ",");
    }

    /**
     * String 转 StringList，自动对每个元素删首尾空
     */
    public static List<String> parseStringList(String str, String regex) {
        if (str == null || regex == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(str.split(regex)).map(String::trim).collect(Collectors.toList());
    }

    /**
     * String 转 StringList，忽略空字符串，自动对每个元素删首尾空
     */
    public static List<String> parseStringListIgnoreBlank(String str) {
        return parseStringListIgnoreBlank(str, ",");
    }

    /**
     * String 转 StringList，忽略空字符串，自动对每个元素删首尾空
     */
    public static List<String> parseStringListIgnoreBlank(String str, String regex) {
        if (str == null || regex == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(str.split(regex)).map(String::trim).filter(StringUtils::notEmpty).collect(Collectors.toList());
    }

    /**
     * String 转 boolean，如果字符串删首尾空后为（true、on、yes、y、1、是、真、开）返回true，否则返回false，不区分大小写
     */
    public static boolean parseBoolean(String str) {
        if (str == null) {
            return false;
        }
        str = str.trim().toLowerCase();
        return "true".equals(str) || "on".equals(str) || "yes".equals(str) || "y".equals(str)
                || "1".equals(str) || "是".equals(str) || "真".equals(str) || "开".equals(str);
    }

    /**
     * 首字母转小写
     */
    public static String toLowerCaseFirstOne(String s) {
        return Character.isLowerCase(s.charAt(0)) ? s : Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }


    /**
     * 首字母转大写
     */
    public static String toUpperCaseFirstOne(String s) {
        return Character.isUpperCase(s.charAt(0)) ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    /**
     * 驼峰命名转下划线命名
     */
    public static String camelToUnderline(String str) {
        if (str == null || isBlank(str)) return "";
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) sb.append("_");
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线命名转驼峰命名
     */
    public static String underlineToCamel(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 分隔文件名
     */
    public static String[] splitFilename(String str) {
        String[] res = new String[]{"", ""};
        if (str == null) {
            return res;
        }
        int i = str.lastIndexOf(".");
        if (i == -1) {
            res[0] = str;
        } else {
            res[0] = str.substring(0, i);
            res[1] = str.substring(i + 1);
        }
        return res;
    }

    /**
     * 判断是否为手机号
     */
    public static boolean isPhone(String str) {
        return Pattern.matches("^1[3456789]\\d{9}$", str);
    }

    /**
     * 隐藏姓名
     */
    public static String hideName(String name) {
        if (isEmpty(name)) return name;
        if (name.length() < 2) return name;
        return hide(name, 0, name.length() - 1);
    }

    /**
     * 隐藏身份证号
     */
    public static String hideIdCard(String card) {
        if (isEmpty(card)) return card;
        if (card.length() < 9) return card;
        return hide(card, 4, card.length() - 4);
    }

    /**
     * 隐藏银行卡
     */
    public static String hideBankCard(String card) {
        if (isEmpty(card)) return card;
        if (card.length() < 5) return card;
        return hide(card, 0, card.length() - 4);
    }

    /**
     * 隐藏手机号
     */
    public static String hidePhone(String phone) {
        if (isEmpty(phone)) return phone;
        if (phone.length() < 8) return phone;
        return hide(phone, 3, phone.length() - 4);
    }

    /**
     * 隐藏手机号
     */
    public static String split(String phone) {
        String str = phone;
        str = str.trim();
        String str2 = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    str2 += str.charAt(i);
                }
            }

        }
        return str2;
    }

    public static void main(String[] args) {
        String phone = split("c18956060852");
        System.out.println(phone);
    }

    /**
     * queryString 转 ParameterMap
     */
    public static ParameterMap<String, String[]> parseParameterMap(String queryString) {
        ParameterMap<String, String[]> map = new ParameterMap<>();
        if (StringUtils.notBlank(queryString)) {
            for (String item : queryString.split("&")) {
                String[] split = item.split("=");
                String name = URLUtil.decode(split[0]);
                if (StringUtils.isBlank(name)) continue;
                String val = URLUtil.decode(split.length == 1 ? "" : split[1]);
                String[] values = map.get(name);
                if (values == null) values = new String[]{val};
                else values = ArrayUtil.append(values, val);
                map.put(name, values);
            }
        }
        return map;
    }

    /**
     * 将url拼接上查询参数，自动将键值对转换成查询参数，例如{'name':'admin','age':18}转换成 'name=admin&age=18'
     *
     * @param {String} url 原始链接
     * @param {Object} data 要转换的键值对
     */
    public static String addQueryString(String url, Map<?, ?> map) {
        if (url == null) url = "";
        String queryString = parseQueryString(map);
        if (queryString.isEmpty()) return url;
        if (!url.contains("?")) url += "?";
        if (!url.endsWith("?") && !url.endsWith("&")) url += "&";
        return url + queryString;
    }

    /**
     * 将键值对转换成查询参数，例如{'name':'admin','age':18}转换成 'name=admin&age=18'，不支持一个参数名多个参数值
     */
    public static String parseQueryString(Map<?, ?> map) {
        if (map == null) return "";
        return join("&", map.entrySet().stream()
                .filter(v -> v.getKey() != null)
                .map(v -> URLUtil.encode(v.getKey().toString()) + "=" + Tools.getNotNull(URLUtil.encode(v.getValue().toString()), ""))
                .collect(Collectors.toList())
        );
    }

}
