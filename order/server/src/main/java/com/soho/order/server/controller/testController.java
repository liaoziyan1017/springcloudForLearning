package com.soho.order.server.controller;

import com.soho.order.server.client.FeignTestClient;
import com.soho.order.server.domain.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class testController {

   /* @Autowired
    private LoadBalancerClient loadBalancerClient;*/

  /* @Autowired
   private RestTemplate restTemplate;*/
  @Autowired
  private FeignTestClient feignTestClient;

    @RequestMapping("/test")
    public Object test(){
       /* RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://localhost:8081/product/msg", String.class);
        log.info(forObject);
        return forObject;*/

       /* RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String s = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/msg";
        log.info(s);
        String forObject = restTemplate.getForObject(s, String.class);
        return forObject;*/


       /* String forObject = restTemplate.getForObject("http://PRODUCT/product/msg", String.class);

        return forObject;*/
        String msg = feignTestClient.msg();
        log.info(msg);
        return msg;

    }

    public static void main(String[] args) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail detail = new OrderDetail();
        detail.setProductId("123");
        detail.setProductQuantity(2);
        orderDetailList.add(detail);
        List<String> productIds = orderDetailList.stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        System.out.println(productIds.get(0));
        System.out.println(9);
    }


}
