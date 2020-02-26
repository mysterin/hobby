package com.linxb.wechat.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/account")
public class PublicAccountController extends BaseController {

    @RequestMapping("/receive")
    public String receive(String msg) {

        logger.info("msg: {}", msg);
        return "success";
    }
}
