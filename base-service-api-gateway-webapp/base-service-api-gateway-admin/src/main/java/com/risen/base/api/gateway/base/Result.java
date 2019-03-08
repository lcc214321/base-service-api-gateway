package com.risen.base.api.gateway.base;

/**
 * 统一数据格式
 *
 * @param <T>
 */
public class Result<T> {

    private int code;

    private String msg;

    private T data;

    public Result() {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
