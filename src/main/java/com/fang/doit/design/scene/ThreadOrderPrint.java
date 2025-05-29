package com.fang.doit.design.scene;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用3个线程有序打印1-100数字
 */
public class ThreadOrderPrint {

    // 1、原子锁
    private static final Object LOCK = new Object();

    // 2、读写锁
    private static final ReentrantLock R_LOCK = new ReentrantLock();

    // 参数
    private static volatile int count = 0;
    private static int MAX = 100;

    public static void main(String[] args) {

//        Thread t1 = new Thread(new SynchronizedWorker(0));
//        Thread t2 = new Thread(new SynchronizedWorker(1));
//        Thread t3 = new Thread(new SynchronizedWorker(2));
//        t1.start();
//        t2.start();
//        t3.start();

        List<Condition> conditions = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            Condition condition = R_LOCK.newCondition();
            conditions.add(condition);
            ConditionWorker conditionWorker = new ConditionWorker(i, conditions);
            conditionWorker.start();
        }
    }


    /**
     * synchronized并发锁 + index标识 + Object_Lock.wait() + Object_Lock.notifyAll()
     */
    static class SynchronizedWorker implements Runnable {
        private final int index;
        public SynchronizedWorker(int index) {
            this.index = index;
        }
        @Override
        public void run() {
            // synchronized 加锁后，notifyAll来识别下一个应执行的线程
            while (count < MAX) {
                synchronized (LOCK) {
                    try {
                        // TODO if -> while 必须使用while一直参与竞争
                        while (count % 3 != index) {
                            // 未到当前线程可执行，等待唤醒
                            LOCK.wait();
                        }
                        if (count < MAX) {
                            System.out.println("Thread-" + index + ":" + count);
                        }
                        count++;
                        LOCK.notifyAll();
                    } catch (Exception ex) {
                        System.out.println("SynchronizedWorker Exception," + ex.getMessage());
                    }
                }
            }
        }
    }


    /**
     * ReentrantLock读写锁 + index + condition.wait() + condition.signal()
     */
    static class ConditionWorker extends Thread {
        int index;
        List<Condition> conditions;
        public ConditionWorker(int index, List<Condition> conditions) {
            this.index = index;
            this.conditions = conditions;
        }

        // 提供顺序通知某一个线程能力
        @Override
        public void run() {
            while (true){
                R_LOCK.lock();
                try {
                    if (count % 3 != index) {
                        conditions.get(index).await();
                    }
                    if(count > 100){
                        signalNext();
                        return;
                    }
                    System.out.println("Thread-" + index + ":" + count);
                    count++;
                    signalNext();

                } catch (Exception ex) {
                    System.out.println("ConditionWorker ex" + ex.getMessage());
                }
            }
        }

        private void signalNext(){
            int nextIndex = (index + 1) % conditions.size();
            conditions.get(nextIndex).signal();
        }
    }



}
