package com.fang.doit.base.juc;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fangfeiyue
 * @Date 2021/1/9 4:41 下午
 */
public class BoundedQueue<T> {

    private Object[] items;
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Object[size];
    }

    public void add(T t) throws InterruptedException {
        lock.lock();

        try {
            while (count == items.length) {
                notFull.await();
//                notFull.await(1000, TimeUnit.HOURS);
            }
            items[addIndex] = t;
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            notFull.signal();
            return (T) x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        String ss = "{\"agencyMode\":1,\"allowSalesmanDividend\":1,\"allowSelfbuyDividend\":1,\"crossRegionCommission\":0,\"goodsJoin\":0,\"hqKdtId\":52903305,\"kdtId\":52903305,\"levelCommission\":[{\"commission\":10000,\"kdtId\":52903305,\"level\":1,\"levelName\":\"省级代理\",\"salesChannelType\":1,\"status\":1},{\"commission\":0,\"kdtId\":52903305,\"level\":2,\"levelName\":\"市级代理\",\"salesChannelType\":1,\"status\":1},{\"commission\":0,\"kdtId\":52903305,\"level\":3,\"levelName\":\"区级代理\",\"salesChannelType\":1,\"status\":1}],\"salesChannelType\":1,\"salesmanCommission\":0,\"settleDelay\":3,\"settleWay\":1,\"unCrossRegionCommission\":10000}";
        System.out.printf(ss);
    }

}
