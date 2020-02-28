package com.linxb.wechat.service.service.impl;

import com.linxb.common.component.service.AbstractService;
import com.linxb.wechat.component.config.PublicAccountConfig;
import com.linxb.wechat.service.service.PublicAccountConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公众号配置服务
 */
@Service
public class PublicAccountConfigServiceImpl extends AbstractService implements PublicAccountConfigService {

    @Autowired
    private PublicAccountConfig publicAccountConfig;

    @Override
    public String getAppId() {
        return publicAccountConfig.getAppId();
    }

    @Override
    public String getAppSecret() {
        return publicAccountConfig.getAppSecret();
    }

    @Override
    public String getToken() {
        return publicAccountConfig.getToken();
    }

    @Override
    public String getencodingAESKey() {
        return publicAccountConfig.getEncodingAESKey();
    }
}
