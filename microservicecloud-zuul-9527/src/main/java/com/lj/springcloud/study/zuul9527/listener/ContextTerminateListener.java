package com.lj.springcloud.study.zuul9527.listener;

import com.netflix.discovery.DiscoveryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

@Configuration
public class ContextTerminateListener implements ApplicationListener<ContextClosedEvent> {

    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println( event.getApplicationContext().getApplicationName()+"服务下线啦~");
        DiscoveryManager.getInstance().shutdownComponent();
    }
}
