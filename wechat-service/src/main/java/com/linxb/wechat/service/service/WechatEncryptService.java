package com.linxb.wechat.service.service;

import com.linxb.wechat.component.dto.MessageVerifyDto;
import com.linxb.wechat.component.dto.PublicAccountMessageDto;
import com.linxb.wechat.component.dto.VerifyDto;

public interface WechatEncryptService {

    /**
     * 公众号接入验证
     * @param verifyDto
     * @return
     */
    boolean verifyPublicAccountAccessMessage(VerifyDto verifyDto);

    /**
     * 公众号收到的加密消息验证
     * @param messageVerifyDto
     * @param msg
     * @return
     */
    boolean verifyPublicAccountEncryptMessage(MessageVerifyDto messageVerifyDto, String msg);

    /**
     * 解析加密的消息体
     * @param encrypt
     * @return
     */
    PublicAccountMessageDto parsePublicAccountMessage(String encrypt);

}
