package com.linxb.wechat.component.dto;

import com.linxb.wechat.component.config.PublicAccountMessageType;

public class PublicAccountMessageDto {
    private String toUserName;
    private String fromUserName;
    private Long createTime;
    private PublicAccountMessageType msgType;
    private Long msgId;

    private String content;

    private String picUrl;
    private String mediaId;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public PublicAccountMessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(PublicAccountMessageType msgType) {
        this.msgType = msgType;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        return "PublicAccountMessageDto{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType=" + msgType +
                ", msgId=" + msgId +
                ", content='" + content + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", mediaId='" + mediaId + '\'' +
                '}';
    }
}
