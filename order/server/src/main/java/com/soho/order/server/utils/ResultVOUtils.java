package com.soho.order.server.utils;

import com.soho.order.server.vo.ResultVO;

public class ResultVOUtils {
    private Integer code;
    private String msg;
    private Object data;

    public static ResultVO success(Object data){
        ResultVO<Object> resultVO = new ResultVO<Object>();
        resultVO.setData(data);
        resultVO.setMsg("成功");
        resultVO.setCode(0);
        return resultVO;
    }
}
