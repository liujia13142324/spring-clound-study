package com.lj.springcloud.study.providerticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("com.lj.springcloud.micoservice.entity")
@EnableHystrix
@EnableCircuitBreaker
//@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(basePackages = "com.lj.springcloud")
@SpringBootApplication
public class ProviderTicketApplication8002 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderTicketApplication8002.class, args);
    }

}
