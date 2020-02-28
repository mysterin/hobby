package com.linxb.wechat.gateway.controller;

import com.linxb.common.component.controller.AbstractController;
import com.linxb.wechat.component.dto.EncryptMessageDto;
import com.linxb.wechat.component.dto.MessageVerifyDto;
import com.linxb.wechat.component.dto.VerifyDto;
import com.linxb.wechat.component.util.WechatUtil;
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
        logger.info("加密参数：{}", messageVerifyDto);
        logger.info("加密的消息体：{}", msg);
        try {
            EncryptMessageDto encryptMessageDto = WechatUtil.getEncryptMessageDto(msg);
            String encrypt = encryptMessageDto.getEncrypt();
            boolean isVerify = wechatEncryptService.verifyPublicAccountEncryptMessage(messageVerifyDto, encrypt);
            if (isVerify) {

                return "success";
            } else {
                logger.error("验证消息失败，加密参数：{}，消息体：{}", messageVerifyDto, msg);
            }
        } catch (Exception e) {
            logger.error("处理信息报错，消息体：{}", msg, e);
        }
        return "";

    }
}
