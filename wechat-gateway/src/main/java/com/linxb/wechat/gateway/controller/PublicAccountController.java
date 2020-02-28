package com.linxb.wechat.gateway.controller;

import com.linxb.common.component.controller.AbstractController;
import com.linxb.common.component.util.StringUtil;
import com.linxb.wechat.component.dto.EncryptMessageDto;
import com.linxb.wechat.component.dto.MessageVerifyDto;
import com.linxb.wechat.component.dto.PublicAccountMessageDto;
import com.linxb.wechat.component.dto.VerifyDto;
import com.linxb.wechat.component.util.PublicAccountMessageUtil;
import com.linxb.wechat.component.util.WechatEncryptUtil;
import com.linxb.wechat.service.service.PublicAccountMessageHandlerService;
import com.linxb.wechat.service.service.WechatEncryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/wechat/public-account")
public class PublicAccountController extends AbstractController {

    @Autowired
    private WechatEncryptService wechatEncryptService;

    @Autowired
    private PublicAccountMessageHandlerService publicAccountMessageHandlerService;

    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    public String receiveGet(VerifyDto verifyDto) throws NoSuchAlgorithmException {
        logger.info("收到参数: {}", verifyDto);
        boolean isverify = wechatEncryptService.verifyPublicAccountAccessMessage(verifyDto);
        if (isverify) {
            return verifyDto.getEchostr();
        }
        return "fail";
    }

    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public String receivePost(MessageVerifyDto messageVerifyDto, @RequestBody String msg) {
        logger.debug("加密参数：{}", messageVerifyDto);
        logger.debug("加密的消息体：{}", msg);
        try {
            EncryptMessageDto encryptMessageDto = WechatEncryptUtil.getEncryptMessageDto(msg);
            String encrypt = encryptMessageDto.getEncrypt();
            boolean isVerify = wechatEncryptService.verifyPublicAccountEncryptMessage(messageVerifyDto, encrypt);
            if (isVerify) {
                PublicAccountMessageDto publicAccountMessageDto = wechatEncryptService.decryptPublicAccountMessage(encrypt);
                logger.debug("解密的消息体：{}", publicAccountMessageDto);
                String responseMessage = publicAccountMessageHandlerService.handleMessage(publicAccountMessageDto);
                if (StringUtil.isNotEmpty(responseMessage)) {
                    String toUserName = publicAccountMessageDto.getFromUserName();
                    String encryptMessage = wechatEncryptService.encryptPublicAccountMessage(responseMessage);
                    String resonseContent = PublicAccountMessageUtil.responseEncryptMessage(toUserName, encryptMessage);
                    logger.debug("返回的消息体：{}", resonseContent);
                    return resonseContent;
                }
            } else {
                logger.error("验证消息失败，加密参数：{}，消息体：{}", messageVerifyDto, msg);
            }
        } catch (Exception e) {
            logger.error("处理信息报错，消息体：{}", msg, e);
        }
        return "";

    }
}
