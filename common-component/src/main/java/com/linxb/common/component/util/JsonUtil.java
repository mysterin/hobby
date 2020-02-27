package com.linxb.common.component.util;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

    public static String toJsonString(Object obj) {
        String jsonString = JSON.toJSONString(obj);
        return jsonString;
    }
}
