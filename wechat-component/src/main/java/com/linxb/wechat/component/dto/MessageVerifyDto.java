package com.linxb.wechat.component.dto;

/**
 * 微信加密的消息参数
 */
public class MessageVerifyDto {
    private String timestamp;
    private String nonce;
    private String msg_signature;
    private String encrypt_type;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getMsg_signature() {
        return msg_signature;
    }

    public void setMsg_signature(String msg_signature) {
        this.msg_signature = msg_signature;
    }

    public String getEncrypt_type() {
        return encrypt_type;
    }

    public void setEncrypt_type(String encrypt_type) {
        this.encrypt_type = encrypt_type;
    }

    @Override
    public String toString() {
        return "MessageVerifyDto{" +
                "timestamp='" + timestamp + '\'' +
                ", nonce='" + nonce + '\'' +
                ", msg_signature='" + msg_signature + '\'' +
                ", encrypt_type='" + encrypt_type + '\'' +
                '}';
    }
}
