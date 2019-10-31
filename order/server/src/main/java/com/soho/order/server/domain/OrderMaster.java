package com.soho.order.server.domain;

import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @description: 模板生成
 * @author: 模板生成
 * @create: ${creatTime}
 **/
@Data
@Entity
public class OrderMaster{

    @Id
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

}
