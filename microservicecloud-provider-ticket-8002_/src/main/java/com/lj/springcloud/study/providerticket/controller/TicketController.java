package com.lj.springcloud.study.providerticket.controller;

import com.lj.springcloud.micoservice.entity.Ticket;
import com.lj.springcloud.micoservice.exception.MyException;
import com.lj.springcloud.micoservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Ticket getOne(@PathVariable int id) throws MyException, Exception {
        Thread.sleep(4000);
        Ticket t = null;
        try {
            t = null;
            t = ticketService.getOne(id);
        } catch (Exception e) {
            MyException my = new MyException();
            my.instanceId = getDatabaseName();
            e.addSuppressed(my);
            throw my;
        }
//        throw  new Exception();
        return t;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Ticket deleteOne(@PathVariable int id){
        return ticketService.deleteOne(id);
    }

    @GetMapping("/list")
    public List<Ticket> findAll() throws InterruptedException {
        return ticketService.list();
    }
    @GetMapping("/page")
    public com.lj.springcloud.micoservice.entity.Page<Ticket> page(@PageableDefault(value = 15 ,sort = { "id" }, direction = Sort.Direction.DESC)
                                     Pageable pageable){
        Page<Ticket> p = ticketService.page(pageable);
        com.lj.springcloud.micoservice.entity.Page<Ticket> result = new com.lj.springcloud.micoservice.entity.Page<>();
        result.setContent(p.getContent());
        result.setPageable(p.getPageable().toString().replaceAll("\\[" , "").replaceAll("]",""));
        result.setTotal(p.getTotalElements());
        result.setTotalPage(p.getTotalPages());
        return result;
    }
    @Autowired
    DataSourceProperties dataSourceProperties;

    @PostMapping("/add")
    public Ticket addOne(@RequestBody Ticket ticket){
        String databseName = getDatabaseName();
        ticket.setDatabaseName(databseName);
        return ticketService.addOne(ticket);
    }

    @PutMapping("/update")
    public Ticket updateOne(Ticket ticket){
        return ticketService.addOne(ticket);
    }

    @GetMapping("/databasename")
    public String getDatabaseName(){
        String url = dataSourceProperties.getUrl();
        String databseName = url.subSequence(url.lastIndexOf("/")+1,url.indexOf("?")).toString();
        System.out.println(databseName);
        return databseName;
    }
    
    @GetMapping("/testForException")
    public void testException() throws Exception {
        throw new Exception("我就是出问题了，怎么招吧~");
    }



}
