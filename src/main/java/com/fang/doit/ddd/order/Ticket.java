package com.fang.doit.ddd.order;

import com.fang.doit.ddd.order.event.DomainEvent;
import com.fang.doit.ddd.order.event.TicketAcceptEvent;
import com.google.common.collect.Lists;

import javax.persistence.Entity;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * ����ۺ�����
 *
 * @author created by fang on 2020/11/12/012 23:26
 */
@Entity(name = "ticket")
public class Ticket {

    /**
     * ����id
     */
    private Long id;

    /**
     * �͹�����id��������ò�Ҫֱ�����ö���
     */
    private Long restaurantId;


    private ZonedDateTime acceptTime;

    /**
     * �ۺ���Ϊ
     * ��Ҫ���������������¼�
     */
    public static void create() {
    }

    public List<DomainEvent> accept(ZonedDateTime readyTime) {
//        ...
        this.acceptTime = acceptTime;
        return Lists.newArrayList(new TicketAcceptEvent(readyTime));

    }
}
