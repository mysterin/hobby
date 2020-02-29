package com.linxb.wechat.service.service.impl;

import com.linxb.wechat.component.constant.PublicAccountMessageType;
import com.linxb.wechat.component.dto.PublicAccountMessageDto;
import com.linxb.wechat.service.service.NovelKeyWordHandlerService;
import org.springframework.stereotype.Service;

@Service
public class NovelKeyWordHandlerServiceImpl implements NovelKeyWordHandlerService {

    @Override
    public PublicAccountMessageDto handle(String keyword) {
        String content = "金牙牙牙牙牙牙";
        PublicAccountMessageDto publicAccountMessageDto = new PublicAccountMessageDto();
        publicAccountMessageDto.setMsgType(PublicAccountMessageType.text);
        publicAccountMessageDto.setContent(content);
        return publicAccountMessageDto;
    }

}
