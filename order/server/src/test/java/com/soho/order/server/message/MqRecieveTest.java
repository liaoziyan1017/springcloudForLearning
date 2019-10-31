package com.soho.order.server.message;

import com.soho.order.server.OrderApplicationTests;
import com.soho.order.server.dto.OrderDTO;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.junit.Assert.*;

@Component
public class MqRecieveTest extends OrderApplicationTests{

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired(required = false)
    private StreamClient streamClient;

    @Test
    public void sendOrder() {
        amqpTemplate.convertAndSend("order", "fruit", "TestOrderFruit: " + new Date());
    }

    @Test
    public void processFruit() {
    }

    @Test
    public void processSteamTest(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123445666");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}