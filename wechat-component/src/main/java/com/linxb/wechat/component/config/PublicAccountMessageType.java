package com.linxb.wechat.component.config;

public enum PublicAccountMessageType {
    TEXT("text"),
    IMAGE("image"),
    VOICE("voice"),
    VIDEO("video"),
    SHORTVICEO("shortvide"),
    LOCATION("location"),
    LINK("link");

    private String type;
    private PublicAccountMessageType(String type) {
        this.type = type;
    }

}
