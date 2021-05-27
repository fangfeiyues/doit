package com.fang.doit.base.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/4/23 11:47
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    countDownLatch.countDown();
                }
            }.start();

        }

        try {
            countDownLatch.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //countDownLatch.
        }
        System.out.println("end");

    }
}
