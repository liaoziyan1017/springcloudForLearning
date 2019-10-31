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
public class OrderDetail{

    @Id
    private String detailId;     
    private String orderId;     
    private String productId;     
    /** 商品名称. */ 
    private String productName;     
    /** 当前价格,单位分. */ 
    private BigDecimal productPrice;     
    /** 数量. */ 
    private Integer productQuantity;     
    /** 小图. */ 
    private String productIcon;     
    /** 创建时间. */ 
    private Date createTime;     
    /** 修改时间. */ 
    private Date updateTime;     

}
