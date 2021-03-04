package com.lj.springcloud.study.providerticket.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

@Component()
public class TestImpl {

    int count=-1;
    int errors = 0;
    @HystrixCommand(fallbackMethod = "test1ErrorHandle")
    public String test2() throws Exception {
        count++;
        if(count%2==0){
            return "success";
        }else{
            System.out.println("test2-> "+(++errors));
            throw new Exception();
        }
    }

    public String test1ErrorHandle() throws Exception {
        throw new Exception("test2 error occured!");
//        return "error occured!";
    }
}
