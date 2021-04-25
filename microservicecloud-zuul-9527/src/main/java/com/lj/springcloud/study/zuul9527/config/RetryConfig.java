package com.lj.springcloud.study.zuul9527.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.discovery.GatewayDiscoveryClientAutoConfiguration;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.socket.client.StandardWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

@Configuration
@AutoConfigureAfter(GatewayDiscoveryClientAutoConfiguration.class)
public class RetryConfig {

  public RetryConfig(DiscoveryLocatorProperties properties){
    properties.getFilters().add(initRetryFilter());
  }


  private FilterDefinition initRetryFilter() {
    FilterDefinition filterDefinition = new FilterDefinition();
    filterDefinition.setName("Retry");
    return filterDefinition;
  }

/* @Bean
  public WebSocketClient webSocketClient(){
    return  new StandardWebSocketClient();
  }*/



}
