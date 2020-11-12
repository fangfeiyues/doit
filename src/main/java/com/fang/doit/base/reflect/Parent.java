package com.fang.doit.base.reflect;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author created by fang on 2020/6/27/027 15:54
 */

public class Parent<T> {

    //用于记录value更新的次数，模拟日志记录的逻辑
    AtomicInteger updateCount = new AtomicInteger();
    private T value;

    //重写toString，输出值和值更新次数
    @Override
    public String toString() {
        return String.format("value: %s updateCount: %d", value, updateCount.get());
    }

    //设置值
    public void setValue(T value) {
        this.value = value;
        updateCount.incrementAndGet();
    }
}
