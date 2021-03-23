package com.lj.springcloud.study.providerticket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ClientRetryTestController {
  @GetMapping("/client/providerRetry1")
  public Object test(HttpServletRequest request){
    System.out.println("请求来啦！");
    return request.getServerPort();
  }
  
}
