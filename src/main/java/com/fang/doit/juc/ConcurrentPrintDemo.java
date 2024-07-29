package com.fang.doit.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.juc
 * @Description:
 * @date Date : 2024-06-20 03:08
 */
public class ConcurrentPrintDemo {


    // 三个线程，分别打印0-100

    private static final Object LOCK = new Object();

    private static volatile int count = 1;

    static class Seq implements Runnable {

        private final int index;

        public Seq(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            // TODO while的含义是？
            while (count < 100) {
                // TODO Lock.wait() 为什么要配合 synchronized 使用？？
                synchronized (LOCK) {
                    try {
                        if (count % 3 != index) {
                            LOCK.wait();
                        }
                        if (count < 100) {
                            System.out.println("Thread-" + index + ":" + count);
                        }
                        count++;
                        LOCK.notifyAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        // 方法一
//        Thread thread1 = new Thread(new Seq(1));
//        Thread thread2 = new Thread(new Seq(2));
//        Thread thread3 = new Thread(new Seq(3));
//        thread1.start();
//        thread2.start();
//        thread3.start();

        // 方法二
        List<Condition> conditions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Condition condition = r_lock.newCondition();
            conditions.add(condition);
            Worker worker = new Worker(i, conditions);
            worker.start();
        }
    }


    private static final ReentrantLock r_lock = new ReentrantLock();
    private static volatile int countIndex = 0;


    static class Worker extends Thread {
        int index;
        // 通过condition可以选择唤醒指定的线程，而synchronized只能选择一个或全部
        List<Condition> conditions;

        public Worker(int index, List<Condition> conditions) {
            super("Thread-" + index);
            this.index = index;
            this.conditions = conditions;
        }

        private void signalNext() {
            // 唤醒index的下一个condition，相比于synchronized的锁这里更灵活
            int nextIndex = (index + 1) % conditions.size();
            conditions.get(nextIndex).signal();
        }

        @Override
        public void run() {
            while (true) {
                r_lock.lock();
                try {
                    // 刚开始要让线程等待，否则可能会导致线程竞争
                    if (countIndex % 3 != index) {
                        conditions.get(index).await();
                    }
                    if (countIndex > 100) {
                        signalNext();
                        return;
                    }
                    System.out.println(this.getName() + ":" + countIndex + "，condition size:" + conditions.size());
                    countIndex++;
                    signalNext();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    r_lock.unlock();
                }
            }
        }
    }

}
