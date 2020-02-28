package com.linxb.wechat.service.service;

import com.linxb.wechat.component.dto.PublicAccountMessageDto;

public interface PublicAccountMessageHandlerService {
    String handleMessage(PublicAccountMessageDto publicAccountMessageDto);
}
