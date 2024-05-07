package com.fang.doit.design.observer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author created by fang on 2020/7/19/019 17:00
 */

public class ObserverAction {
    private Object target;
    private Method method;

    public ObserverAction(Object target, Method method) {
//        this.target = Preconditions.checkNotNull(target);
        this.method = method;
        this.method.setAccessible(true);
    }

    public void execute(Object event) { // event��method�����Ĳ���
        try {
            method.invoke(target, event);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
