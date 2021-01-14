package com.fang.doit.base.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fangfeiyue
 * @Date 2021/1/9 3:47 下午
 */
public class ConditionUseCase {

    Lock lock = new ReentrantLock();
    Condition acondition = lock.newCondition();
    Condition bcondition = lock.newCondition();

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            // 中断发生异常
            acondition.await();
        } finally {
            lock.unlock();
        }
    }

    public void conditionSignal() {
        lock.lock();
        try {
            acondition.signal();
        } finally {
            lock.unlock();
        }
    }
}
