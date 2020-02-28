package com.linxb.wechat.component.util;

import com.linxb.common.component.util.SecurityUtil;
import com.linxb.common.component.util.XmlUtil;
import com.linxb.wechat.component.dto.EncryptMessageDto;
import org.dom4j.DocumentException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

public class WechatUtil {

    public static EncryptMessageDto getEncryptMessageDto(String msgXml) throws DocumentException {
        Map<String, String> map = XmlUtil.parseXml(msgXml);
        EncryptMessageDto encryptMessageDto = new EncryptMessageDto();
        encryptMessageDto.setToUserName(map.get("ToUserName"));
        encryptMessageDto.setEncrypt(map.get("Encrypt"));
        return encryptMessageDto;
    }

    public static String decrypt(String encrypt, byte[] aesKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
        IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        byte[] encrypted = SecurityUtil.base64DecodeToBytes(encrypt);
        byte[] original = cipher.doFinal(encrypted);
        byte[] bytes = pkcs7Decode(original);
        // 分离16位随机字符串,网络字节序和AppId
        byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

        int xmlLength = recoverNetworkBytesOrder(networkOrder);

        String xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), Charset.forName("UTF-8"));
        String from_appid = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length),
                Charset.forName("UTF-8"));
        return xmlContent;
    }

    /**
     * 删除解密后明文的补位字符
     *
     * @param decrypted 解密后的明文
     * @return 删除补位字符后的明文
     */
    public static byte[] pkcs7Decode(byte[] decrypted) {
        int pad = (int) decrypted[decrypted.length - 1];
        if (pad < 1 || pad > 32) {
            pad = 0;
        }
        return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
    }

    // 还原4个字节的网络字节序
    public static int recoverNetworkBytesOrder(byte[] orderBytes) {
        int sourceNumber = 0;
        for (int i = 0; i < 4; i++) {
            sourceNumber <<= 8;
            sourceNumber |= orderBytes[i] & 0xff;
        }
        return sourceNumber;
    }
}
