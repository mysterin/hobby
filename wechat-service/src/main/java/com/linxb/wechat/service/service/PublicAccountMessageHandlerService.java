package com.linxb.wechat.service.service;

import com.linxb.common.component.util.DateUtil;
import com.linxb.wechat.component.constant.PublicAccountMessageType;
import com.linxb.wechat.component.dto.PublicAccountMessageDto;
import com.linxb.wechat.component.util.PublicAccountMessageUtil;

public interface PublicAccountMessageHandlerService {

    String buildResponseMessage(PublicAccountMessageDto publicAccountMessageDto);

    default String buildResponseTextMessage(String toUserName, String fromUserName, String content) {
        Long createTime = DateUtil.timestamp();
        PublicAccountMessageDto responseMessage = new PublicAccountMessageDto();
        responseMessage.setToUserName(toUserName);
        responseMessage.setFromUserName(fromUserName);
        responseMessage.setCreateTime(createTime);
        responseMessage.setMsgType(PublicAccountMessageType.text);
        responseMessage.setContent(content);
        String message = PublicAccountMessageUtil.messageObjectToXml(responseMessage);
        return message;
    }
}
