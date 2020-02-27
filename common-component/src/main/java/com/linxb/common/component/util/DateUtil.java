package com.linxb.common.component.util;

import java.util.Date;

/**
 * 时间类工具
 */
public class DateUtil {

    public static long timestamp() {
        Date now = new Date();
        return timestamp(now);
    }

    public static long timestamp(Date date) {
        return date.getTime();
    }
}
