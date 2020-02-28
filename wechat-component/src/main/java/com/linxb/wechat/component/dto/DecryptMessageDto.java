package com.linxb.wechat.component.dto;

public class DecryptMessageDto {
    private String appId;
    private String content;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DecryptMessageDto{" +
                "appId='" + appId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
