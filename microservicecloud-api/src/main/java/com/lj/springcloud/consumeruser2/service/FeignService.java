package com.lj.springcloud.consumeruser2.service;

import com.lj.springcloud.consumeruser2.service.fallbackfactory.TicketClientFallbackFactory;
import com.lj.springcloud.micoservice.entity.Ticket;
import com.lj.springcloud.micoservice.exception.MyException;
//import TicketClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(value = "PROVIDER-TICKET" , fallbackFactory = TicketClientFallbackFactory.class)
@FeignClient(value = "PROVIDER-TICKET" /*, fallbackFactory = TicketClientFallbackFactory.class*/)
public interface FeignService {
    @GetMapping("/ticket/{id}")
    public Ticket getOne(@PathVariable int id) throws MyException;
    @DeleteMapping("/ticket/{id}")
    public Ticket deleteOne(@PathVariable int id);
    @PostMapping({"/ticket/add"})
    public Ticket addOne(Ticket ticket);
    @PutMapping({"/ticket/update"})
    public Ticket updateOne(Ticket ticket);
    @GetMapping("/ticket/page")
    public com.lj.springcloud.micoservice.entity.Page<Ticket> page(Pageable pageAble);
    @GetMapping("/ticket/list")
    public List<Ticket> list();

    @GetMapping("/ticket/testForException")
    void testException();

    @GetMapping("/hystrix/test1")
    public String test1();
    @PostMapping("/hystrix/test3")
    void test3(com.alibaba.fastjson.JSONObject params);
    
    @GetMapping("/hystrix/test2")
    String test2();
    
    @GetMapping("/client/providerRetry1")
    Object testRetry();
    
}
