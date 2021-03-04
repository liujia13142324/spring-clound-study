package com.lj.springcloud.consumeruser2.controller;

import com.lj.springcloud.consumeruser2.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/retry")
public class RetrTestController {
  
  @Autowired
  FeignService feignService;
  
  @GetMapping("/test1")
  public Object test(HttpServletRequest request){
    return request.getServerPort();
  }
  @GetMapping("/test2")
  public Object test2(HttpServletRequest request){
    return request.getServerPort()+"_"+feignService.testRetry();
  }
}
