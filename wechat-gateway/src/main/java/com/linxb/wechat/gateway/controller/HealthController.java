package com.linxb.wechat.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health")
@RestController
public class HealthController {

    @RequestMapping("/test")
    public String test() {
        return "success";
    }
}
