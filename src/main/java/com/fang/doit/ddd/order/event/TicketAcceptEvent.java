package com.fang.doit.ddd.order.event;

import java.time.ZonedDateTime;

/**
 * �����¼�
 *
 * @author created by fang on 2020/11/12/012 23:45
 */
public class TicketAcceptEvent extends TicketDomainEvent {

    private ZonedDateTime time;

    public TicketAcceptEvent(ZonedDateTime time) {
        this.time = time;
    }
}
