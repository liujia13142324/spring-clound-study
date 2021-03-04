package com.lj.springcloud.study.providerticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.lj.springcloud.micoservice.entity")
@SpringBootApplication
public class ProviderTicketApplication8003 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderTicketApplication8003.class, args);
    }

}
