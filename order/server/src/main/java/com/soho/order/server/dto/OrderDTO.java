package com.soho.order.server.dto;

import com.soho.order.server.domain.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private String orderId;
    /** 买家名字. */
    private String buyerName;
    /** 买家电话. */
    private String buyerPhone;
    /** 买家地址. */
    private String buyerAddress;
    /** 买家微信openid. */
    private String buyerOpenid;
    /** 订单总金额. */
    private BigDecimal orderAmount;
    /** 订单状态, 默认为新下单. */
    private int orderStatus;
    /** 支付状态, 默认未支付. */
    private int payStatus;
    /** 创建时间. */
    private Date createTime;
    /** 修改时间. */
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
