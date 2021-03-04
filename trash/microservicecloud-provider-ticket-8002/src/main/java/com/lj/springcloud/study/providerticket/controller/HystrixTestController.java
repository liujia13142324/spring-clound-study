package com.lj.springcloud.study.providerticket.controller;

import com.alibaba.fastjson.JSONObject;
import com.lj.springcloud.study.providerticket.service.impl.TestImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hystrix")
public class HystrixTestController {

    int count = -1;
    int errors = 0;
    @Autowired
    TestImpl test;

    @GetMapping("/test1")
    @HystrixCommand(
        fallbackMethod = "test1ErrorHandle",
        commandProperties = {
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="20000"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
        }
    )
    public String test1() throws Exception {
        return "8002的success";
    }
    
    //必须和上面的返回值是一样的
    public String test1ErrorHandle(){
        return "8002的系统繁忙! error occured!";
    }
    
    @GetMapping("/test2")
    public String test2() throws Exception {
        return test.test2();
    }

    @PostMapping("/test3")
    public void test3(@RequestBody JSONObject params){
        System.out.println(params);
    }

}
