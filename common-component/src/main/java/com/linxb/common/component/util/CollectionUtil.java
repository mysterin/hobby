package com.linxb.common.component.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 集合工具类
 */
public class CollectionUtil {

    public static String sortAndAppend(List<String> list) {
        Collections.sort(list);
        String str = "";
        for (String s : list) {
            str += s;
        }
        return str;
    }

    public static String formParams(Map<String, Object> map) {
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        String str = "";
        for (Map.Entry<String, Object> entry : entries) {
            str += entry.getKey() + "=" + entry.getValue() + "&";
        }
        if (str.length() > 0) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }
}
