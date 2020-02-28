package com.linxb.wechat.service.service.impl;

import com.linxb.common.component.service.AbstractService;
import com.linxb.common.component.util.DateUtil;
import com.linxb.wechat.component.config.PublicAccountMessageType;
import com.linxb.wechat.component.dto.PublicAccountMessageDto;
import com.linxb.wechat.component.util.PublicAccountMessageUtil;
import com.linxb.wechat.service.service.PublicAccountMessageHandlerService;
import org.springframework.stereotype.Service;

@Service
public class PublicAccountMessageHandlerServiceImpl extends AbstractService implements PublicAccountMessageHandlerService {

    @Override
    public String handleMessage(PublicAccountMessageDto publicAccountMessageDto) {
        String toUserName = publicAccountMessageDto.getFromUserName();
        String fromUserName = publicAccountMessageDto.getToUserName();
        Long createTime = DateUtil.timestamp();
        PublicAccountMessageType msgType = PublicAccountMessageType.text;
        String content = "welcome";

        PublicAccountMessageDto responseMessage = new PublicAccountMessageDto();
        responseMessage.setToUserName(toUserName);
        responseMessage.setFromUserName(fromUserName);
        responseMessage.setCreateTime(createTime);
        responseMessage.setMsgType(msgType);
        responseMessage.setContent(content);

        String message = PublicAccountMessageUtil.messageObjectToXml(publicAccountMessageDto);

        return message;
    }
}
