package com.lj.springcloud.study.zuul9527;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringCloudApplication
@EnableZuulProxy
public class Zuul9527Application {

    public static void main(String[] args) {
        SpringApplication.run(Zuul9527Application.class, args);
    }

}
