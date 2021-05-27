package com.fang.doit.thought.ddd.order.event;

import java.util.List;
import java.util.function.Function;

/**
 * @author created by fang on 2020/11/12/012 23:55
 */
public abstract class AbstractAggregateDomainEventPublisher<A, E extends DomainEvent> {

    private Function<A, Object> idSupplier;

    private DomainEventPublisher eventPublisher;

    private Class<A> aggregateType;

    protected AbstractAggregateDomainEventPublisher(DomainEventPublisher eventPublisher, Class<A> aggregateType, Function<A, Object> idSupplier) {
        this.aggregateType = aggregateType;
        this.eventPublisher = eventPublisher;
        this.idSupplier = idSupplier;
    }

    public void publish(A aggregate, List<E> events) {
        eventPublisher.publish(aggregateType.getTypeName(), idSupplier.apply(aggregate), (List<DomainEvent>) events);

    }
}
