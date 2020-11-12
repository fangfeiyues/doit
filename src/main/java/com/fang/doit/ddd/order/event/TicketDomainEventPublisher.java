package com.fang.doit.ddd.order.event;

import com.fang.doit.ddd.order.Ticket;

import java.util.function.Function;

public class TicketDomainEventPublisher extends AbstractAggregateDomainEventPublisher<Ticket, TicketDomainEvent> {

    protected TicketDomainEventPublisher(DomainEventPublisher eventPublisher, Class<Ticket> aggregateType, Function<Ticket, Object> idSupplier) {
        super(eventPublisher, aggregateType, idSupplier);
    }
}
