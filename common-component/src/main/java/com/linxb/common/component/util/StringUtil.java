package com.linxb.common.component.util;

import java.util.Objects;

/**
 * 字符串工具类
 */
public class StringUtil {

    public static boolean compare(String s1, String s2) {
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
}
