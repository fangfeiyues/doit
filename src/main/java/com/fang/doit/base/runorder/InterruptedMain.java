package com.fang.doit.base.runorder;

import java.util.Objects;
import java.util.concurrent.locks.LockSupport;

/**
 * @author by Feiyue on 2020/4/13 3:54 PM
 */
public class InterruptedMain {

    public static void main(String[] args) {

        InterruptedTest test = new InterruptedTest();

        test.test();
    }

    static class InterruptedTest {

        private Object lock = new Object();

        public void test() {

            System.out.println("test");

            new Thread(() -> {

                System.out.println("LockSupport park");

                LockSupport.park(lock);
            });

            System.out.println(Thread.interrupted());


        }
    }
}
