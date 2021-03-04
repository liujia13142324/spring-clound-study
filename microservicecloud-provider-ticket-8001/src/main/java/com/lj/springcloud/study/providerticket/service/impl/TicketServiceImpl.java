package com.lj.springcloud.study.providerticket.service.impl;

import com.lj.springcloud.annotation.OperationLogger;
import com.lj.springcloud.micoservice.entity.Ticket;
import com.lj.springcloud.micoservice.service.TicketService;
import com.lj.springcloud.study.providerticket.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Ticket getOne(int id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public Ticket deleteOne(int id) {
        Ticket t = getOne(id);
        ticketRepository.deleteById(id);
        return t;
    }

    @Override
    @OperationLogger(option = "添加一个ticket")
    public Ticket addOne(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> list() {
        return ticketRepository.findAll();
    }

    @Override
    public Page<Ticket> page(Pageable pageAble) {
        return ticketRepository.findAll(pageAble);
    }
}
