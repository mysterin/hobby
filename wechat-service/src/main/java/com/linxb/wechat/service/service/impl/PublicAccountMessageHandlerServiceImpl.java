package com.linxb.wechat.service.service.impl;

import com.linxb.common.component.service.AbstractService;
import com.linxb.wechat.component.constant.PublicAccountMessageType;
import com.linxb.wechat.component.dto.PublicAccountMessageDto;
import com.linxb.wechat.service.service.PublicAccountMessageHandlerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 处理公众号发送的消息
 */
@Service
public class PublicAccountMessageHandlerServiceImpl extends AbstractService implements PublicAccountMessageHandlerService {

    @Resource(name = "publicAccountTextMessageHandlerServiceImpl")
    private PublicAccountMessageHandlerService publicAccountMessageHandlerService;

    @Override
    public String buildResponseMessage(PublicAccountMessageDto publicAccountMessageDto) {
        String responseMessage = null;
        PublicAccountMessageType msgType = publicAccountMessageDto.getMsgType();
        switch (msgType) {
            case text:
                responseMessage = publicAccountMessageHandlerService.buildResponseMessage(publicAccountMessageDto);
                break;
            default:
                break;
        }
        return responseMessage;
    }
}
