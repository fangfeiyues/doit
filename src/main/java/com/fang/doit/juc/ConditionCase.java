package com.fang.doit.juc;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.base.juc
 * @Description: Condition实现生产者-消费者模型
 * @date Date : 2024-05-07 5:26 下午
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCase {
    /**
     * Lock
     */
    private final Lock lock = new ReentrantLock();
    /**
     * 数组未满
     */
    private final Condition notFull = lock.newCondition();
    /**
     * 数组非空
     */
    private final Condition notEmpty = lock.newCondition();
    /**
     * 存储数据的底层数组
     */
    private final Object[] items = new Object[5];
    /**
     * 输入数据的索引位置
     */
    private int inputIndex;
    /**
     * 输出数据的索引位置
     */
    private int outputIndex;
    /**
     * 计数器
     */
    private int count;
    /**
     * 日期格式化
     */
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");


    /**
     * 生产者方法，往数组里面写数据
     *
     * @param input 输入数据
     * @throws InterruptedException 中断异常
     */
    public void put(Object input) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                System.out.println("producer list is full, start wait..");
                // 数组已满，没有空间时，挂起等待，直到数组“非满”（notFull）
                notFull.await();
            }
            items[inputIndex] = input;
            if (++inputIndex == items.length) {
                inputIndex = 0;
            }
            ++count;
            // 因为放入了一个数据，数组肯定不是空的了，此时唤醒等待这notEmpty条件上的线程
            System.out.println("producer list is not empty, start signal..");
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费者方法，从数组里面拿数据
     *
     * @return 数据
     * @throws InterruptedException 中断异常
     */
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                System.out.println("consumer list is empty, start wait..");
                // 数组是空的，没有数据可拿时，挂起等待，直到数组非空（notEmpty）
                notEmpty.await();
            }
            Object x = items[outputIndex];
            if (++outputIndex == items.length) {
                outputIndex = 0;
            }
            --count;
            // 因为拿出了一个数据，数组肯定不是满的了 此时唤醒等待这notFull条件上的线程
            System.out.println("consumer list is not full, start signal..");
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 打印时间
     */
    private static String printDate() {
        return "【当前时间：" + SIMPLE_DATE_FORMAT.format(new Date()) + "】";
    }


    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newCachedThreadPool();
            ConditionCase conditionConsumeCase = new ConditionCase();
            // 10个生产者线程
            for (int i = 0; i < 10; i++) {
                executorService.submit(() -> {

                    for (int j = 0; j < 10; j++) {
                        try {
                            Thread.sleep(10);
                            conditionConsumeCase.put(String.format("生产者%s于%s生产一条数据", Thread.currentThread().getName(), printDate()));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            // 1个消费者线程
            executorService.submit(() -> {
                try {
                    for (int i = 0; i < 100; i++) {
                        Object outPut = conditionConsumeCase.take();
                        System.out.printf("消费者获取到数据%s%n", outPut);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } finally {
            assert executorService != null;
            executorService.shutdown();
        }

    }

}
