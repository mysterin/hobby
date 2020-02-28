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
        byte[] bytes = StringUtil.getBytes(str);
        return sha1(bytes);
    }

    public static String sha1(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        byte[] digest = messageDigest.digest(bytes);
        String str = Hex.encodeHexString(digest);
        return str;
    }

    public static String base64Encode(String str) {
        byte[] bytes = StringUtil.getBytes(str);
        return base64Encode(bytes);
    }

    public static String base64Encode(byte[] bytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(bytes);
        String s = new String(encode, StringUtil.DEFAULT_CHARSET);
        return s;
    }

    public static String base64Decode(String encodeStr) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(encodeStr);
        String s = new String(bytes, StringUtil.DEFAULT_CHARSET);
        return s;
    }

    public static byte[] base64DecodeToBytes(String encodeStr) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(encodeStr);
        return bytes;
    }
}
