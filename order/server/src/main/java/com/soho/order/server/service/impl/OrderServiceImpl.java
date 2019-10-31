package com.soho.order.server.service.impl;

import com.soho.order.server.dao.OrderDetailDao;
import com.soho.order.server.dao.OrderMasterDao;
import com.soho.order.server.domain.OrderDetail;
import com.soho.order.server.domain.OrderMaster;
import com.soho.order.server.dto.OrderDTO;
import com.soho.order.server.enums.OrderStatusEnum;
import com.soho.order.server.enums.PayStatusEnum;
import com.soho.order.server.service.OrderService;
import com.soho.order.server.utils.EMath;
import com.soho.order.server.utils.KeyUtil;
import com.soho.product.client.ProductClient;
import com.soho.product.common.ProductDecreaseInput;
import com.soho.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterDao orderMasterDao;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private ProductClient productClient;
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String key = KeyUtil.genUniqueKey();
        // 获得产品信息，调用商品接口
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        List<String> productIds = orderDetailList.stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> listForOrder = productClient.getListForOrder(productIds);

        // 计算总价
        String orderAmount = "0";
        for(OrderDetail detail : orderDetailList){
            for(ProductInfoOutput info : listForOrder){
                if(info.getProductId().equals(detail.getProductId())){
                    orderAmount = EMath.add(orderAmount, EMath.multiply(detail.getProductQuantity(), info.getProductPrice(), 2), 2);
                    BeanUtils.copyProperties(info, detail);
                    detail.setOrderId(key);
                    detail.setDetailId(KeyUtil.genUniqueKey());
                    orderDetailDao.save(detail);
                }
            }
        }


        // 扣库存，调用商品接口
        List<ProductDecreaseInput> productDecreaseInputList = orderDetailList.stream()
                .map(e -> new ProductDecreaseInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(productDecreaseInputList);

        //商品入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(key);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(orderAmount));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);

        return orderDTO;
    }
}
