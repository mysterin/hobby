package com.linxb.wechat.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PublicAccountBasicConfig {

    @Value("${wechat.publicAccount.token}")
    private String token;

    @Value("${wechat.publicAccount.encodingAESKey}")
    private String encodingAESKey;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }
}
