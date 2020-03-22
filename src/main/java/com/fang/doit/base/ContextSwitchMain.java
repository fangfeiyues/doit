package com.fang.doit.base;

/**
 * @author created by fang on 2020/3/22/022 15:22
 */
public class ContextSwitchMain {
    public static void main(String[] args) {
        //���ж��߳�
        MultiThreadTester test1 = new MultiThreadTester();
        test1.Start();
        //���е��߳�
        SerialTester test2 = new SerialTester();
        test2.Start();
    }


    static class MultiThreadTester extends ThreadContextSwitchTester {
        @Override
        public void Start() {
            long start = System.currentTimeMillis();
            MyRunnable myRunnable1 = new MyRunnable();
            Thread[] threads = new Thread[4];
            //��������߳�
            for (int i = 0; i < 4; i++) {
                threads[i] = new Thread(myRunnable1);
                threads[i].start();
            }
            for (int i = 0; i < 4; i++) {
                try {
                    //�ȴ�һ��������
                    threads[i].join();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("multi thread exce time: " + (end - start) + "s");
            System.out.println("counter: " + counter);
        }

        // ����һ��ʵ��Runnable����
        class MyRunnable implements Runnable {
            public void run() {
                while (counter < 100000000) {
                    // synchronized �����µľ����Ӷ������������ĵ��л�
                    synchronized (this) {
                        if (counter < 100000000) {
                            increaseCounter();
                        }

                    }
                }
            }
        }
    }

    //����һ�����߳�
    static class SerialTester extends ThreadContextSwitchTester {
        @Override
        public void Start() {
            long start = System.currentTimeMillis();
            for (long i = 0; i < count; i++) {
                increaseCounter();
            }
            long end = System.currentTimeMillis();
            System.out.println("serial exec time: " + (end - start) + "s");
            System.out.println("counter: " + counter);
        }
    }

    //����
    static abstract class ThreadContextSwitchTester {
        public static final int count = 100000000;
        public volatile int counter = 0;

        public int getCount() {
            return this.counter;
        }

        public void increaseCounter() {

            this.counter += 1;
        }

        public abstract void Start();
    }
}
