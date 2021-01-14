package com.fang.doit.base.juc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author fangfeiyue
 * @Date 2021/1/8 5:49 下午
 */
public class FairAndUnfairTest {

    private static Lock fairLock = new ReentrantLock2(true);

    private static Lock unFairLock = new ReentrantLock2(false);

    private static class ReentrantLock2 extends ReentrantLock {

        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThread() {
            // 同步队列线程
            List<Thread> arrayList = new ArrayList<Thread>(super.getQueuedThreads());
            return arrayList;
        }

    }
}
