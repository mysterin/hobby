package com.linxb.common.component.util;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

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

    public static String base64Decode(String encodeStr) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(encodeStr);
        String s = new String(bytes, "UTF-8");
        return s;
    }

    public static byte[] base64DecodeToBytes(String encodeStr) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(encodeStr);
        return bytes;
    }
}
