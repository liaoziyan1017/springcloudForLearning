package com.soho.order.server.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "參數錯誤"),
    CAR_EMPTY(2, "購物車爲空");
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
