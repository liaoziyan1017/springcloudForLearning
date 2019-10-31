package com.soho.order.server.message;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqRecieve {

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("order"),
            value = @Queue("computerOrder"),
            key = "computer"))
    public void processPC(String message){
        log.info("processPC : {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("order"),
            key = "fruit",
            value = @Queue("fruitOrder")
    ))
    public void processFruit(String message){
        log.info("processFruit : {}", message);
    }




}
