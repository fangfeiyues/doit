package com.fang.doit.ddd.order;

import com.fang.doit.ddd.order.event.DomainEvent;
import com.fang.doit.ddd.order.event.TicketAcceptEvent;
import com.google.common.collect.Lists;

import javax.persistence.Entity;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * 后厨聚合领域
 *
 * @author created by fang on 2020/11/12/012 23:26
 */
@Entity(name = "ticket")
public class Ticket {

    /**
     * 领域id
     */
    private Long id;

    /**
     * 餐馆领域id，这里最好不要直接引用对象
     */
    private Long restaurantId;


    private ZonedDateTime acceptTime;

    /**
     * 聚合行为
     * 主要是用于生成领域事件
     */
    public static void create() {
    }

    public List<DomainEvent> accept(ZonedDateTime readyTime) {
//        ...
        this.acceptTime = acceptTime;
        return Lists.newArrayList(new TicketAcceptEvent(readyTime));

    }
}
