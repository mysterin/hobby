package com.linxb.wechat.component.util;

import com.linxb.common.component.util.RandomUtil;
import com.linxb.common.component.util.SecurityUtil;
import com.linxb.common.component.util.StringUtil;
import com.linxb.common.component.util.XmlUtil;
import com.linxb.wechat.component.constant.WechatMessageKey;
import com.linxb.wechat.component.dto.DecryptMessageDto;
import com.linxb.wechat.component.dto.EncryptMessageDto;
import org.dom4j.DocumentException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

public class WechatEncryptUtil {

    public static EncryptMessageDto getEncryptMessageDto(String msgXml) throws DocumentException {
        Map<String, String> map = XmlUtil.parseXml(msgXml);
        EncryptMessageDto encryptMessageDto = new EncryptMessageDto();
        encryptMessageDto.setToUserName(map.get(WechatMessageKey.TO_USER_NAME));
        encryptMessageDto.setEncrypt(map.get(WechatMessageKey.ENCRYPT));
        return encryptMessageDto;
    }

    /**
     * 微信消息体加密
     * @param appId
     * @param aesKey
     * @param text
     * @return
     * @throws Exception
     */
    public static String encrypt(String appId, byte[] aesKey, String text) throws Exception {
        ByteGroup byteCollector = new ByteGroup();
        String randomStr = RandomUtil.getRandomStr(16);
        byte[] randomStrBytes = StringUtil.getBytes(randomStr);
        byte[] textBytes = StringUtil.getBytes(text);
        byte[] networkBytesOrder = getNetworkBytesOrder(textBytes.length);
        byte[] appidBytes = StringUtil.getBytes(appId);

        // randomStr + networkBytesOrder + text + appid
        byteCollector.addBytes(randomStrBytes);
        byteCollector.addBytes(networkBytesOrder);
        byteCollector.addBytes(textBytes);
        byteCollector.addBytes(appidBytes);

        // ... + pad: 使用自定义的填充方式对明文进行补位填充
        byte[] padBytes = PKCS7Encoder.encode(byteCollector.size());
        byteCollector.addBytes(padBytes);

        // 获得最终的字节流, 未加密
        byte[] unencrypted = byteCollector.toBytes();

        // 设置加密模式为AES的CBC模式
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
        IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

        // 加密
        byte[] encrypted = cipher.doFinal(unencrypted);

        // 使用BASE64对加密后的字符串进行编码
        String base64Encrypted = SecurityUtil.base64Encode(encrypted);

        return base64Encrypted;
    }

    /**
     * 微信消息解密
     * @param encrypt
     * @param aesKey
     * @return
     * @throws Exception
     */
    public static DecryptMessageDto decrypt(String encrypt, byte[] aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
        IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        byte[] encrypted = SecurityUtil.base64DecodeToBytes(encrypt);
        byte[] original = cipher.doFinal(encrypted);
        byte[] bytes = PKCS7Encoder.decode(original);
        // 分离16位随机字符串,网络字节序和AppId
        byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

        int xmlLength = recoverNetworkBytesOrder(networkOrder);

        String xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), Charset.forName("UTF-8"));
        String from_appid = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length),
                Charset.forName("UTF-8"));
        DecryptMessageDto decryptMessageDto = new DecryptMessageDto();
        decryptMessageDto.setAppId(from_appid);
        decryptMessageDto.setContent(xmlContent);
        return decryptMessageDto;
    }

    /**
     * 还原3个字节的网络字节序
     * @param orderBytes
     * @return
     */
    public static int recoverNetworkBytesOrder(byte[] orderBytes) {
        int sourceNumber = 0;
        for (int i = 0; i < 4; i++) {
            sourceNumber <<= 8;
            sourceNumber |= orderBytes[i] & 0xff;
        }
        return sourceNumber;
    }

    /**
     * 生成4个字节的网络字节序
     * @param sourceNumber
     * @return
     */
    public static byte[] getNetworkBytesOrder(int sourceNumber) {
        byte[] orderBytes = new byte[4];
        orderBytes[3] = (byte) (sourceNumber & 0xFF);
        orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
        orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
        orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
        return orderBytes;
    }
}
