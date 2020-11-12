package com.fang.doit.base.reflect;

import java.util.Arrays;

/**
 * @author created by fang on 2020/6/27/027 15:55
 */
public class Child1 extends Parent<String> {

    @Override
    public void setValue(String value) {
        System.out.println("Child1.setValue called");
        super.setValue(value);
    }

    public static void main(String[] args) {

        Child1 child1 = new Child1();
        Arrays.stream(child1.getClass().getMethods())
                .filter(method -> method.getName().equals("setValue") && !method.isBridge())
                .forEach(method -> {
                    try {
                        method.invoke(child1, "test");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        System.out.println(child1.toString());
    }

}
