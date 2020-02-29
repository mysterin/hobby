package com.linxb.wechat.service.service.impl;

import com.linxb.wechat.component.constant.PublicAccountMessageType;
import com.linxb.wechat.component.constant.WechatTextMessageKeyWord;
import com.linxb.wechat.component.dto.PublicAccountMessageDto;
import com.linxb.wechat.service.service.NovelKeyWordHandlerService;
import com.linxb.wechat.service.service.PublicAccountMessageHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicAccountTextMessageHandlerServiceImpl implements PublicAccountMessageHandlerService {

    @Autowired
    private NovelKeyWordHandlerService novelKeyWordHandlerService;

    @Override
    public String buildResponseMessage(PublicAccountMessageDto publicAccountMessageDto) {
        String content = publicAccountMessageDto.getContent();
        String toUserName = publicAccountMessageDto.getFromUserName();
        String fromUserName = publicAccountMessageDto.getToUserName();

        String responseContent = null;
        PublicAccountMessageDto responseMessageDto = null;
        switch (content) {
            case WechatTextMessageKeyWord.JIANLAI:
            case WechatTextMessageKeyWord.FANREN:
            case WechatTextMessageKeyWord.ZHUIXU:
            case WechatTextMessageKeyWord.GUIMI:
                responseMessageDto = novelKeyWordHandlerService.handle(content);
                break;
            default:
                break;
        }
        if (responseMessageDto != null) {
            responseContent = buildResponseTextMessage(toUserName, fromUserName, responseMessageDto.getContent());
        }
        return responseContent;
    }

}
