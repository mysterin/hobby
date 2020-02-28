package com.linxb.wechat.component.config;

import com.linxb.common.component.util.SecurityUtil;

public class PublicAccountConfig {

    private String appId;
    private String appSecret;
    private String token;
    private String encodingAESKey;
    private byte[] aesKey;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

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

    public byte[] getAesKey() {
        if (aesKey == null) {
            aesKey = SecurityUtil.base64DecodeToBytes(encodingAESKey + "=");
        }
        return aesKey;
    }

    public void setAesKey(byte[] aesKey) {
        this.aesKey = aesKey;
    }
}
