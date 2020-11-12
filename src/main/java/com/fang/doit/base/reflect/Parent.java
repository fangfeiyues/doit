package com.fang.doit.base.reflect;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author created by fang on 2020/6/27/027 15:54
 */

public class Parent<T> {

    //���ڼ�¼value���µĴ�����ģ����־��¼���߼�
    AtomicInteger updateCount = new AtomicInteger();
    private T value;

    //��дtoString�����ֵ��ֵ���´���
    @Override
    public String toString() {
        return String.format("value: %s updateCount: %d", value, updateCount.get());
    }

    //����ֵ
    public void setValue(T value) {
        this.value = value;
        updateCount.incrementAndGet();
    }
}
