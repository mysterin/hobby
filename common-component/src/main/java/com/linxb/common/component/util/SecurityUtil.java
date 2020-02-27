package com.linxb.common.component.util;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 安全加解密工具
 */
public class SecurityUtil {

    public static String sha1(String str) throws NoSuchAlgorithmException {
        byte[] bytes = str.getBytes(Charset.defaultCharset());
        return sha1(bytes);
    }

    public static String sha1(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        byte[] digest = messageDigest.digest(bytes);
        String str = Hex.encodeHexString(digest);
        return str;
    }
}
