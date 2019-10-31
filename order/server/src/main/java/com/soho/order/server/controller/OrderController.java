package com.soho.order.server.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soho.order.server.domain.OrderDetail;
import com.soho.order.server.dto.OrderDTO;
import com.soho.order.server.enums.ResultEnum;
import com.soho.order.server.exception.OrderException;
import com.soho.order.server.form.OrderForm;
import com.soho.order.server.service.OrderService;
import com.soho.order.server.utils.ResultVOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    private static ObjectMapper objectMapper = new ObjectMapper().configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);;

    @PostMapping("/create")
    public Object create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        //校验参数
        if(bindingResult.hasErrors()){
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        try {
            System.out.println(orderForm.getItems());
            List<OrderDetail> orderDetailList = objectMapper.readValue(orderForm.getItems(), new TypeReference<List<OrderDetail>>() { });
            if(orderDetailList.isEmpty()){
                throw new OrderException(ResultEnum.CAR_EMPTY);
            }
            orderDTO.setOrderDetailList(orderDetailList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new OrderException(ResultEnum.PARAM_ERROR);
    }

        //产生订单
        orderDTO = orderService.create(orderDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderDTO.getOrderId());
        return ResultVOUtils.success(map);
    }

}
