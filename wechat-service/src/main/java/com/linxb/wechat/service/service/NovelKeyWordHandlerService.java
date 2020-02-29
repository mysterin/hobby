package com.linxb.wechat.service.service;

import com.linxb.wechat.component.dto.PublicAccountMessageDto;

public interface NovelKeyWordHandlerService {
    PublicAccountMessageDto handle(String keyword);
}
