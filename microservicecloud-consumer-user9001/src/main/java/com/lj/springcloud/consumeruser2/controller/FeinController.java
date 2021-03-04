package com.lj.springcloud.consumeruser2.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lj.springcloud.consumeruser2.service.FeignService;
import com.lj.springcloud.micoservice.entity.Page;
import com.lj.springcloud.micoservice.entity.Ticket;
import com.lj.springcloud.micoservice.exception.MyException;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/feign")
public class FeinController {

    @Autowired
    private FeignService ticketFeignService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping(value = "/ticket/{id}",method = RequestMethod.GET)
    public Ticket getOne(@PathVariable int id) throws MyException {
        System.out.println( simpleDateFormat.format(new Date()) );
        return ticketFeignService.getOne(id);
    }

    @RequestMapping(value = "/ticket/{id}",method = RequestMethod.DELETE)
    public void deleteOne(@PathVariable int id){
        ticketFeignService.deleteOne(id);
    }

    @GetMapping("/ticket/list")
    public List<Ticket> findAll(){
        return ticketFeignService.list();
    }

    @GetMapping("/ticket/page")
    public Page<Ticket> getEntryByPageable(@PageableDefault(value = 15 ,sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable){
        Page<Ticket> p = ticketFeignService.page(pageable);
        return p;
    }

    @PostMapping("/ticket/add")
    public Ticket addOne(Ticket ticket) throws JsonProcessingException {
       return ticketFeignService.addOne(ticket);
    }

    @PutMapping("/ticket/update")
    public void updateOne(Ticket ticket) throws JsonProcessingException {
        ticketFeignService.updateOne(ticket);
    }
    
    
    @GetMapping("/test2")
    public String test2(){
        return ticketFeignService.test2();
    }
    
    
    //JSON类型的参数，前端传来的必须是json字符串，@RequestBody也不能少（会使用HttpMessageConverter）
    @PostMapping("/test3")
    public void test3( @RequestBody JSONObject params){
        ticketFeignService.test3(params);
    }

    private int count;

    @GetMapping("/CBLCZLException")
    public void testException(){
        if( count %20==0 ){
            System.out.println(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        }
        System.out.println(++count);
      ticketFeignService.testException();
    }
    
    
}
