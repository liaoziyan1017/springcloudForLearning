package com.soho.order.server.exception;

import com.soho.order.server.enums.ResultEnum;
import lombok.Data;

@Data
public class OrderException extends RuntimeException{
    private Integer code;

    public OrderException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
