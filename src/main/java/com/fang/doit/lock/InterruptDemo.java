package com.fang.doit.lock;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/5/30 10:11
 */
public class InterruptDemo {

    /**
     * 线程的中断 挂起 --
     *
     * @param args
     */
    //public static void main(String[] args) {
    //
    //    NThread nThread = new NThread();
    //    System.out.println("interrupt执行前");
    //    nThread.start();
    //    try {
    //        Thread.sleep(3000);
    //    } catch (InterruptedException e) {
    //        e.printStackTrace();
    //    }
    //    nThread.interrupt();
    //    System.out.println("interrupt执行后");
    //}
    //
    ///**
    // * 测试多线程的中断机制
    // */
    //static class NThread extends Thread {
    //    @Override
    //    public void run() {
    //        super.run();
    //        while (true) {
    //            System.out.println("依然存活...");
    //            try {
    //                Thread.sleep(1000);
    //            } catch (InterruptedException e) {
    //                e.printStackTrace();
    //            }
    //        }
    //    }
    //}

    /**
     * Created by Zero on 2017/8/17.
     */
    public static void main(String[] args) {
        NThread nThread = new NThread();
        nThread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("interrupt执行前" + System.currentTimeMillis());
        nThread.interrupt();
    }

    /**
     * 测试多线程的中断机制
     */
    static class NThread extends Thread {

        @Override
        public void run() {
            /**
             * 对于非阻塞中的线程，只是改变了中断状态，即Thread.isInterrupted() 将返回true；
             * 对于可取消的阻塞状态中的线程，比如等待在这些函数上的线程，Thread.sleep()、Object.wait()、Thread.join(), 这个线程收到中断信号后，会抛出InterruptedException，同时会把中断状态置回为true。
             * 但调用Thread.interrupted()
             * 会对中断状态进行复位。
             */
            while (!interrupted()) {
                System.out.println("依然存活...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                    //抛出InterruptedException 的同时，线程的中断标志被清除了
                    System.out.println("InterruptedException");

                    // 如果这里不做interrupt()将会一直下去 很危险！
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("interrupt执行后" + System.currentTimeMillis());
        }
    }
}


