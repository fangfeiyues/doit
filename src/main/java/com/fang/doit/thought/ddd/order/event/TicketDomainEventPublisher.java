package com.fang.doit.thought.ddd.order.event;

import com.fang.doit.thought.ddd.order.Ticket;

import java.util.function.Function;

public class TicketDomainEventPublisher extends AbstractAggregateDomainEventPublisher<Ticket, TicketDomainEvent> {

    protected TicketDomainEventPublisher(DomainEventPublisher eventPublisher, Class<Ticket> aggregateType, Function<Ticket, Object> idSupplier) {
        super(eventPublisher, aggregateType, idSupplier);
    }
}
