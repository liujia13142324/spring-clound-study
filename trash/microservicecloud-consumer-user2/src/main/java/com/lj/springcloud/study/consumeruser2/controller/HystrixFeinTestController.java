package com.lj.springcloud.study.consumeruser2.controller;

import com.lj.springcloud.study.consumeruser2.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign2")
public class HystrixFeinTestController {

    @Autowired
    FeignService feignService;

    @GetMapping("/test1")
    public String test1(){
        return feignService.test1();
    }
}
