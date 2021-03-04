package com.lj.springcloud.study.providerticket.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

@Component()
public class TestImpl {

    int count=-1;

    @HystrixCommand(fallbackMethod = "test1ErrorHandle")
    public String test2() throws Exception {
        count++;
        if(count%2==0){
            return "success";
        }else{
            throw new Exception();
        }
    }

    public String test1ErrorHandle(){
        return "error occured!";
    }
}
