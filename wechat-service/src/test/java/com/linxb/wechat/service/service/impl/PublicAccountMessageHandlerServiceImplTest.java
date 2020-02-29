package com.linxb.wechat.service.service.impl;

import com.linxb.common.component.test.AbstractSpringBootTest;
import com.linxb.wechat.component.dto.PublicAccountMessageDto;
import com.linxb.wechat.service.service.PublicAccountMessageHandlerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PublicAccountMessageHandlerServiceImplTest extends AbstractSpringBootTest {

    @Autowired
    private PublicAccountMessageHandlerService publicAccountMessageHandlerService;

    @Test
    public void handleMessage() {
        PublicAccountMessageDto publicAccountMessageDto = new PublicAccountMessageDto();
        publicAccountMessageDto.setToUserName("gh_bd74d9636d92");
        publicAccountMessageDto.setFromUserName("oBngoxKaRAk6sxzPJTzDC5fX9dTQ");
        String message = publicAccountMessageHandlerService.buildResponseMessage(publicAccountMessageDto);
        logger.info(message);
    }
}
