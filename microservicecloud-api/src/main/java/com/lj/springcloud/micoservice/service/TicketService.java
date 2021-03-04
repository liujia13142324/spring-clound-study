package com.lj.springcloud.micoservice.service;

import com.lj.springcloud.micoservice.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketService {
    public Ticket getOne(int id);
    public Ticket deleteOne(int id);
    public Ticket addOne(Ticket ticket);
    public Page<Ticket> page(Pageable pageAble);
    public List<Ticket> list();
}
