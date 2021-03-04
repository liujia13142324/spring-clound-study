package com.lj.springcloud.consumeruser2.service.fallbackfactory;

import com.alibaba.fastjson.JSONObject;
import com.lj.springcloud.micoservice.entity.Page;
import com.lj.springcloud.micoservice.entity.Ticket;
import com.lj.springcloud.micoservice.exception.MyException;
import com.lj.springcloud.consumeruser2.service.FeignService;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class TicketClientFallbackFactory implements FallbackFactory<FeignService> {

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Override
    public FeignService create(Throwable throwable) {
        throwable.printStackTrace();
        final Throwable t = throwable;

        return new FeignService() {
            @Override
            public Ticket getOne(int id) throws MyException{
                String instanceId = "";
                if(t instanceof  MyException){
                    instanceId = ((MyException)t).instanceId;
                }
                Ticket ticket = new Ticket(-1,"该票暂不存在！",0,instanceId);
                return ticket;
            }

            @Override
            public Ticket deleteOne(int id) {
                return null;
            }

            @Override
            public Ticket addOne(Ticket ticket) {
                return null;
            }

            @Override
            public Ticket updateOne(Ticket ticket) {
                return null;
            }

            @Override
            public Page<Ticket> page(Pageable pageAble) {
                return null;
            }

            @Override
            public List<Ticket> list() {
                Ticket ticket = new Ticket(-1,"看到这个消息，说明已经发生了异常",0,"");
                return Arrays.asList(ticket);
            }
            
    
            @Override
            public void testException() {
                System.out.println(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
            }
    
            @Override
            public String test1() {
                System.out.println("test1 error ocur");
                return "test1 error ocur";
            }

            @Override
            public void test3(JSONObject params) {
                System.out.println("test3 error ocur");
            }
    
            @Override
            public String test2() {
                System.out.println("test2 error ocur");
                return "test2 error ocur";
            }
    
            @Override
            public Object testRetry() {
                return "retry error";
            }
        };
    }
}
