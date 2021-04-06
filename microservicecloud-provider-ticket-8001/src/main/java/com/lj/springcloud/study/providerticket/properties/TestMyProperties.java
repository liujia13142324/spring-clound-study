package com.lj.springcloud.study.providerticket.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;

@Configuration
@ConfigurationProperties("test")
@Data
public class TestMyProperties {

    @Value("${spring.application.name}")
    String applicationName;
    
    String name;
    String sex;
    String[] habit;
    HashMap<String,String> education;
    
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
