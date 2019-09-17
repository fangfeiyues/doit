package com.fang.doit.base;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author by Feiyue on 2019/9/4 2:52 PM
 */
public class OptionalMain {

    public static void main(String[] args) {
        List<String> list = null;
//        List<String> list = new ArrayList<String>(2);
//        list.add("1");
//        list.add("2");

        Optional<List<String>> noEmpty = Optional.ofNullable(list);

        // ifPresent 方法接受lambda表达式作为参数
        noEmpty.ifPresent(li -> System.out.println(li));

        // 有值则将其返回，否则返回指定的其它值
        List<String> a = noEmpty.orElse(Arrays.asList("33"));
        // 可以接受Supplier接口的实现用来生成默认值
        noEmpty.orElseGet(() -> Arrays.asList("44"));
        try {
            noEmpty.orElseThrow(Exception::new);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // map操作
        noEmpty.map(li -> li.add("3"));

        // 过滤
        noEmpty.filter(li -> li.size() > 3);



    }


}
