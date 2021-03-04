package com.lj.springcloud.study.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
//@EnableTurbine
@EnableDiscoveryClient
public class HystrixdashboardApplication_9001 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixdashboardApplication_9001.class, args);
    }

}
