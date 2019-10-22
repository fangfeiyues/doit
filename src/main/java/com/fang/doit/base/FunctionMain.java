package com.fang.doit.base;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author by Feiyue on 2019/9/17 8:48 PM
 */
public class FunctionMain {

    public static void main(String[] args) {

        // Function<key,value> 接受一个参数有返回值
        Function<Integer, String> function = (x) -> "result:" + x;
        System.out.println(function.apply(1));

        // Predicate 接受一个输入参数，返回布尔值
        Predicate<String> predicate = (x) -> x.length() > 0;
        predicate.test("String");

        // Consumer 接受一个输入参数，无返回值
        Consumer<String> consumer = x -> System.out.println("consumer: " + x);
        consumer.accept("Hello");

        // Supplier 无参
        Supplier<String> supplier = () -> "Test supplier";
        System.out.println(supplier.get());

        // 大抵都是传入一个参数 获取到返回值
    }
}
