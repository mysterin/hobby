package com.linxb.wechat.gateway.controller;

import com.linxb.common.component.util.CollectionUtil;
import com.linxb.common.component.util.SecurityUtil;
import com.linxb.common.component.util.StringUtil;
import com.linxb.wechat.gateway.config.PublicAccountBasicConfig;
import com.linxb.wechat.gateway.dto.VerifyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wechat/public-account")
public class PublicAccountController extends BaseController {

    @Autowired
    private PublicAccountBasicConfig publicAccountBasicConfig;

    @RequestMapping("/receive")
    public String receive(VerifyDto verifyDto) throws NoSuchAlgorithmException {
        logger.info("收到参数: {}", verifyDto);
        List<String> list = new ArrayList<>();
        list.add(publicAccountBasicConfig.getToken());
        list.add(verifyDto.getTimestamp());
        list.add(verifyDto.getNonce());
        String str = CollectionUtil.sortAndAppend(list);
        String encodeStr = SecurityUtil.sha1(str);

        if (StringUtil.compare(verifyDto.getSignature(), encodeStr)) {
            return verifyDto.getEchostr();
        }
        return "fail";
    }
}
