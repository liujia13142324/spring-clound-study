package com.lj.springcloud.micoservice.service;

import org.springframework.stereotype.Service;

@Service
public class LoggerServiceImpl implements LoggerService{
    @Override
    public void addLogger(String process, String methodName) {
        System.out.println(process+"LoggerServiceImpl.addLogger 添加到数据库...");
    }
}
