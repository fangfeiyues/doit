package com.fang.doit.thought.design.decorator;

/**
 * created by fang on 2018/5/3/003 22:27
 */
public class DecoratorTest {

    public static void main(String[] args) {

        Sourcable source = new Source();

        // 装饰类对象
        Sourcable obj = new Decorator1(new Decorator2(new Decorator3(source)));

        obj.operation();
    }
}
