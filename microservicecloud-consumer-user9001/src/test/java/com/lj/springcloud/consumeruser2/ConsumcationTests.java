package com.lj.springcloud.consumeruser2;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.hystrix.HystrixProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Arrays;

@SpringBootTest
class ConsumcationTests implements ApplicationContextAware {

    @Test
    void contextLoads() {
        HystrixCommandAspect aspect = (HystrixCommandAspect) applicationContext.getBean("hystrixCommandAspect");
        System.out.println(aspect);
        System.out.println(JSONObject.toJSONString(aspect));
    }
    
    ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
