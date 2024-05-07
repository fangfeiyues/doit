package com.fang.doit.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by Feiyue on 2020/4/13 7:01 PM
 */
public class ReentrantLockCase {

    public static void main(String[] args) throws InterruptedException {
        BufferInterruptibility buff = new BufferInterruptibility();

        final Writer writer = new Writer(buff);
        final Reader reader = new Reader(buff);

        writer.start();
        Thread.sleep(1000);
        reader.start();


        ExecutorService pool = Executors.newCachedThreadPool();

        pool.submit(()->{
            long start = System.currentTimeMillis();
            for (;;) {
                if (System.currentTimeMillis()
                        - start > 10000) {
                    System.out.println("不等了，尝试中断");
                    reader.interrupt();
                    break;
                }

            }
        });

    }

    public static class BufferInterruptibility {

        private ReentrantLock lock = new ReentrantLock();

        public void write() {
            lock.lock();
            try {
                long startTime = System.currentTimeMillis();
                System.out.println("开始往这个buff写入数据…");
                for (; ; )// 模拟要处理很长时间
                {
                    if (System.currentTimeMillis()
                            - startTime > Integer.MAX_VALUE) {
                        break;
                    }
                }
                System.out.println("终于写完了");
            } finally {
                lock.unlock();
            }
        }

        public void read() throws InterruptedException {
            lock.lockInterruptibly();//注意这里，可以响应中断，抛出中断异常。
            try {
                System.out.println("从这个buff读数据");
            } finally {
                lock.unlock();
            }
        }

    }

    public static class Writer extends Thread {

        private BufferInterruptibility buff;

        public Writer(BufferInterruptibility buff) {
            this.buff = buff;
        }

        @Override
        public void run() {
            buff.write();
            System.out.println("写结束");
        }

    }

    public static class Reader extends Thread {

        private BufferInterruptibility buff;

        public Reader(BufferInterruptibility buff) {
            this.buff = buff;
        }

        @Override
        public void run() {

            try {
                buff.read();//可以收到中断的异常，从而有效退出
            } catch (InterruptedException e) {
                System.out.println("我不读了");
            }

            System.out.println("读结束，去做其它事情");

        }

    }
}
