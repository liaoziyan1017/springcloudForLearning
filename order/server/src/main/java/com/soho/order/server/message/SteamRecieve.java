package com.soho.order.server.message;

import com.soho.order.server.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class SteamRecieve {

    @StreamListener(value = StreamClient.INPUT)
    @SendTo(StreamClient.INPUT2)
    public String process(OrderDTO dto){
        log.info("---------------------------------------SteamRecieved: {} "+
                "--------------------------------------------------------" ,  dto);
        return "recieved";
    }

    @StreamListener(value = StreamClient.INPUT2)
    public void process2(String message){
        log.info("------------------------------------------steamReciever : {}" +
                "--------------------------------------------------" , message);
    }



}
