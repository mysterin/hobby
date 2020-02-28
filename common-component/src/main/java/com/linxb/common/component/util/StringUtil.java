package com.linxb.common.component.util;

import java.nio.charset.Charset;
import java.util.Objects;

/**
 * 字符串工具类
 */
public class StringUtil {

    public static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public static boolean isEqual (String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null || s2 == null) {
            return false;
        }
        return s1.equals(s2);
    }

    public static String toString(Object obj) {
        return Objects.toString(obj);
    }

    public static Long parseLong(String s) {
        return Long.parseLong(s);
    }

    public static byte[] getBytes(String text) {
        return text.getBytes(DEFAULT_CHARSET);
    }

    public static boolean isEmpty(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String s) {
        return isEmpty(s) == false;
    }
}
