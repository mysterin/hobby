package com.linxb.wechat.service.service.impl;

import com.linxb.common.component.service.AbstractService;
import com.linxb.common.component.util.CollectionUtil;
import com.linxb.common.component.util.SecurityUtil;
import com.linxb.common.component.util.StringUtil;
import com.linxb.wechat.component.dto.DecryptMessageDto;
import com.linxb.wechat.component.dto.MessageVerifyDto;
import com.linxb.wechat.component.dto.PublicAccountMessageDto;
import com.linxb.wechat.component.dto.VerifyDto;
import com.linxb.wechat.component.util.PublicAccountMessageUtil;
import com.linxb.wechat.component.util.WechatEncryptUtil;
import com.linxb.wechat.service.service.PublicAccountConfigService;
import com.linxb.wechat.service.service.WechatEncryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信加解密服务
 */
@Service
public class WechatEncryptServiceImpl extends AbstractService implements WechatEncryptService {

    @Autowired
    private PublicAccountConfigService publicAccountConfigService;

    @Override
    public boolean verifyPublicAccountAccessMessage(VerifyDto verifyDto) {
        try {
            List<String> list = new ArrayList<>();
            String token = publicAccountConfigService.getToken();
            list.add(token);
            list.add(verifyDto.getTimestamp());
            list.add(verifyDto.getNonce());
            String str = CollectionUtil.sortAndAppend(list);
            String encodeStr = SecurityUtil.sha1(str);

            if (StringUtil.isEqual(verifyDto.getSignature(), encodeStr)) {
                return true;
            }
        } catch (Exception e) {
            logger.error("验证微信公众号服务器失败", e);
        }
        return false;
    }

    @Override
    public boolean verifyPublicAccountEncryptMessage(MessageVerifyDto messageVerifyDto, String msg) {
        try {
            List<String> list = new ArrayList<>();
            String token = publicAccountConfigService.getToken();
            list.add(token);
            list.add(messageVerifyDto.getTimestamp());
            list.add(messageVerifyDto.getNonce());
            list.add(msg);
            String str = CollectionUtil.sortAndAppend(list);
            logger.debug("消息参数排序组合：{}", str);
            String encodeStr = SecurityUtil.sha1(str);

            if (StringUtil.isEqual(messageVerifyDto.getMsg_signature(), encodeStr)) {
                return true;
            }
        } catch (Exception e) {
            logger.error("验证微信公众号消息失败", e);
        }
        return false;
    }

    /**
     * 解密公众号消息体
     * @param encrypt
     * @return
     */
    @Override
    public PublicAccountMessageDto decryptPublicAccountMessage(String encrypt) {
        String appId = publicAccountConfigService.getAppId();
        byte[] aesKey = publicAccountConfigService.getAESKey();
        PublicAccountMessageDto publicAccountMessageDto = null;
        try {
            DecryptMessageDto decryptMessageDto = WechatEncryptUtil.decrypt(encrypt, aesKey);
            if (StringUtil.isEqual(appId, decryptMessageDto.getAppId())) {
                String content = decryptMessageDto.getContent();
                publicAccountMessageDto = PublicAccountMessageUtil.xmlToMessageObject(content);
            } else {
                logger.error("解密消息失败：{}", decryptMessageDto);
            }

        } catch (Exception e) {
            logger.error("解密消息报错", e);
        }
        return publicAccountMessageDto;
    }

    @Override
    public String encryptPublicAccountMessage(String message) {
        try {
            String appId = publicAccountConfigService.getAppId();
            byte[] aesKey = publicAccountConfigService.getAESKey();
            String encrypt = WechatEncryptUtil.encrypt(appId, aesKey, message);
            return encrypt;
        } catch (Exception e) {
            logger.error("加密消息失败", e);
        }
        return null;
    }
}
