package com.soho.order.server.controller;

import com.soho.order.server.dto.OrderDTO;
import com.soho.order.server.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stream")
public class SteamController {


    @Autowired(required = false)
    private StreamClient streamClient;

    @GetMapping("/process")
    public void processSteamTest(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123445666");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
