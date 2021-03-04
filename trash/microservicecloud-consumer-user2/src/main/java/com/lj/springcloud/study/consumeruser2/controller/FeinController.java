package com.lj.springcloud.study.consumeruser2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lj.springcloud.micoservice.entity.Page;
import com.lj.springcloud.micoservice.entity.Ticket;
import com.lj.springcloud.micoservice.exception.MyException;
import com.lj.springcloud.study.consumeruser2.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feign2")
public class FeinController {

    @Autowired
    private FeignService ticketFeignService;


    @RequestMapping(value = "/ticket/{id}",method = RequestMethod.GET)
    public Ticket getOne(@PathVariable int id) throws MyException {
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



}
