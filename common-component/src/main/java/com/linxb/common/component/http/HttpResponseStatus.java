package com.linxb.common.component.http;

public enum HttpResponseStatus {

    SUCCESS(0, "success"),
    SERVER_ERROR(-1, "server error");

    private int code;
    private String msg;

    private HttpResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
