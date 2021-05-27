package com.fang.doit.thought.ddd.order;

import com.fang.doit.thought.ddd.order.event.DomainEvent;
import com.fang.doit.thought.ddd.order.event.TicketDomainEventPublisher;
import com.fang.doit.thought.ddd.order.repository.TicketRepository;

import javax.annotation.Resource;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author created by fang on 2020/11/12/012 23:26
 */
public class KitchenService {

    @Resource
    private TicketRepository ticketRepository;

    /**
     * £¿£¿£¿
     */
    @Resource
    private TicketDomainEventPublisher ticketDomainEventPublisher;

    public void accept(Long ticketId, ZonedDateTime acceptTime) {
        Ticket ticket = ticketRepository.findById(ticketId);
        List<DomainEvent> acceptEvents = ticket.accept(acceptTime);
//        ticketDomainEventPublisher.publish(ticket, acceptEvents);
    }
}
