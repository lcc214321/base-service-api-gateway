package com.risen.base.api.gateway.base;

/**
 * 统一错误码
 */
public enum ErrorCode {
    Ok(0),
    InternalError(500);

    private int value;

    ErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
