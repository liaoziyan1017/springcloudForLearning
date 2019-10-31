package com.soho.order.server.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product")
public interface FeignTestClient {
    @GetMapping("/product/msg")
    String msg();
}
