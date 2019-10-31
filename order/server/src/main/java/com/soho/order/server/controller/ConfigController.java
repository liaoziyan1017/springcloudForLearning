package com.soho.order.server.controller;


import com.soho.order.server.config.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {
    @Autowired
    private GirlConfig girlConfig;
    @Value("${env}")
    private String env;

    @GetMapping("/test1")
    public String test1(){
        return env;
    }

    @GetMapping("/test")
    public String test(){
        return "--------"+girlConfig.getAge() + girlConfig.getName()+"-------------";
    }

}
