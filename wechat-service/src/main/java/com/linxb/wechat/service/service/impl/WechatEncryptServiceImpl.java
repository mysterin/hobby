package com.linxb.wechat.service.service.impl;

import com.linxb.common.component.service.AbstractService;
import com.linxb.common.component.util.CollectionUtil;
import com.linxb.common.component.util.SecurityUtil;
import com.linxb.common.component.util.StringUtil;
import com.linxb.wechat.component.dto.MessageVerifyDto;
import com.linxb.wechat.component.dto.PublicAccountMessageDto;
import com.linxb.wechat.component.dto.VerifyDto;
import com.linxb.wechat.service.service.PublicAccountConfigService;
import com.linxb.wechat.service.service.WechatEncryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public PublicAccountMessageDto parsePublicAccountMessage(String encrypt) {

        return null;
    }
}
