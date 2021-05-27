package com.fang.doit.base.lock;

/**
 * created by fang on 2019/4/11/011 23:18
 */
public class SynchronizedDemo {

    private final Object object = new Object();

    private void sync() {

        synchronized (object) {

        }
    }
}
