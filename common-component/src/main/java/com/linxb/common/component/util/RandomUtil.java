package com.linxb.common.component.util;

import java.util.Random;

/**
 * 随机数工具类
 */
public class RandomUtil {

    public static int getRandomNumber() {
        double random = Math.random();
        return (int) (random * 10000);
    }

    public static String getRandomString(int length){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<length; i++){
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
