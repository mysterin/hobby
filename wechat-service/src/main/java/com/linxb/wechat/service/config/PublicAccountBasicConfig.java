package com.linxb.wechat.service.config;

import com.linxb.wechat.component.config.PublicAccountConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublicAccountBasicConfig {

    @Value("${wechat.publicAccount.appId}")
    private String appId;

    @Value("${wechat.publicAccount.appSecret")
    private String appSecret;

    @Value("${wechat.publicAccount.token}")
    private String token;

    @Value("${wechat.publicAccount.encodingAESKey}")
    private String encodingAESKey;

    @Bean
    public PublicAccountConfig publicAccountConfig() {
        PublicAccountConfig publicAccountConfig = new PublicAccountConfig();
        publicAccountConfig.setAppId(appId);
        publicAccountConfig.setAppSecret(appSecret);
        publicAccountConfig.setToken(token);
        publicAccountConfig.setEncodingAESKey(encodingAESKey);
        return publicAccountConfig;
    }
}
