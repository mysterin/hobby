package com.linxb.common.component.http;

public class HttpResponse {
    private int code;
    private String msg;
    private Object data;

    public static HttpResponse build(HttpResponseStatus httpResponseStatus) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.code = httpResponseStatus.getCode();
        httpResponse.msg = httpResponseStatus.getMsg();
        return httpResponse;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
