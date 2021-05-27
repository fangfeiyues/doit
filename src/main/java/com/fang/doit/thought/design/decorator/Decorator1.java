package com.fang.doit.thought.design.decorator;

/**
 * created by fang on 2018/5/3/003 22:24
 */
public class Decorator1 implements Sourcable {

    private Sourcable sourcable;

    public Decorator1(Sourcable sourcable) {
        super();
        this.sourcable = sourcable;
    }

    public void operation() {
        System.out.println("第1个装饰器前");
        sourcable.operation();
        System.out.println("第1个装饰器后");
    }
}
