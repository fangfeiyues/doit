package com.fang.doit.spring.aop;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.io.IOException;


@Component
public class TransactionTest {

    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * 添加事务注解
     * 1.使用 propagation 指定事务的传播行为, 即当前的事务方法被另外一个事务方法调用时如何使用事务, 默认取值为 REQUIRED, 即使用调用方法的事务 REQUIRES_NEW: 事务自己的事务, 调用的事务方法的事务被挂起.
     * 2.使用 isolation 指定事务的隔离级别, 最常用的取值为 READ_COMMITTED
     * 3.默认情况下 Spring 的声明式事务对所有的运行时异常进行回滚. 也可以通过对应的属性进行设置. 通常情况下去默认值即可.
     * 4.使用 readOnly 指定事务是否为只读. 表示这个事务只读取数据但不更新数据,这样可以帮助数据库引擎优化事务. 若真的事一个只读取数据库值的方法, 应设置 readOnly=true
     * 5.使用 timeout 指定强制回滚之前事务可以占用的时间.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = IOException.class,
            timeout = 3)
    public void addUser() {

        add1();

        add2();
    }

    /**
     * <!--开启aspectj代理，并暴露aop代理到ThreadLocal-->
     * <aop:aspectj-autoproxy expose-proxy="true"/>
     */
    private void add1() {
        System.out.println("add1");
    }

    private void add2() {

        System.out.println("add2");
//        int i = 1 / 0;
    }

}
