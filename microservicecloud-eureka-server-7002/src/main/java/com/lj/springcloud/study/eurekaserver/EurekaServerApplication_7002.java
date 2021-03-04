package com.lj.springcloud.study.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication_7002 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication_7002.class, args);
    }

}
