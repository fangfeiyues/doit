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

        // Function<key,value> ����һ�������з���ֵ
        Function<Integer, String> function = (x) -> "result:" + x;
        System.out.println(function.apply(1));

        // Predicate ����һ��������������ز���ֵ
        Predicate<String> predicate = (x) -> x.length() > 0;
        predicate.test("String");

        // Consumer ����һ������������޷���ֵ
        Consumer<String> consumer = x -> System.out.println("consumer: " + x);
        consumer.accept("Hello");

        // Supplier �޲�
        Supplier<String> supplier = () -> "Test supplier";
        System.out.println(supplier.get());

        // ��ֶ��Ǵ���һ������ ��ȡ������ֵ
    }
}
