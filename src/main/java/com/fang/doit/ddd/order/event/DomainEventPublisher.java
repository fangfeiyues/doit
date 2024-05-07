package com.fang.doit.ddd.order.event;

import java.util.List;

public interface DomainEventPublisher {

    void publish(String aggregateType, Object aggregateId, List<DomainEvent> domainEvents);
}
