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
                    System.out.println("�����ˣ������ж�");
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
                System.out.println("��ʼ�����buffд�����ݡ�");
                for (; ; )// ģ��Ҫ����ܳ�ʱ��
                {
                    if (System.currentTimeMillis()
                            - startTime > Integer.MAX_VALUE) {
                        break;
                    }
                }
                System.out.println("����д����");
            } finally {
                lock.unlock();
            }
        }

        public void read() throws InterruptedException {
            lock.lockInterruptibly();//ע�����������Ӧ�жϣ��׳��ж��쳣��
            try {
                System.out.println("�����buff������");
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
            System.out.println("д����");
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
                buff.read();//�����յ��жϵ��쳣���Ӷ���Ч�˳�
            } catch (InterruptedException e) {
                System.out.println("�Ҳ�����");
            }

            System.out.println("��������ȥ����������");

        }

    }
}
