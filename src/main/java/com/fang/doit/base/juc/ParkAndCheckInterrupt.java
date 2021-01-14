package com.fang.doit.base.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author fangfeiyue
 * @Date 2021/1/5 5:08 下午
 */
public class ParkAndCheckInterrupt {

    private final boolean parkAndCheckInterrupt() {
        LockSupport.park(this);
        return Thread.interrupted();
    }


    public static void main(String[] args) throws InterruptedException {
        ParkAndCheckInterrupt SPCK = new ParkAndCheckInterrupt();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Park!");
                if (SPCK.parkAndCheckInterrupt()) {
                    System.out.println("parkAndCheckInterrupt!");

                } else {
                    System.out.println("Unpark!");
                }
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("start interrupt");
//        thread.interrupt();
        LockSupport.unpark(thread);
    }
}
