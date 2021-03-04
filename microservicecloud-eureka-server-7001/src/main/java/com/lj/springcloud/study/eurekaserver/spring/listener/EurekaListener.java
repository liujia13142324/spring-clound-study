package com.lj.springcloud.study.eurekaserver.spring.listener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonBuilderUtils;

@Configuration
public class EurekaListener implements ApplicationListener<EurekaInstanceRegisteredEvent> {
  @Override
  public void onApplicationEvent(EurekaInstanceRegisteredEvent event) {
    System.out.println("注册了新的实例："+new Gson().toJson(event.getInstanceInfo()));
  }
  
  @Configuration
  class EurekaCanceledListener implements ApplicationListener<EurekaInstanceCanceledEvent>{
    @Override
    public void onApplicationEvent(EurekaInstanceCanceledEvent event) {
      System.out.println("注销了新的实例："+new Gson().toJson(event));
    }
  }
  
  
}
