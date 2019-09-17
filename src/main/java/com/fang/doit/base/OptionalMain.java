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

        // ifPresent ��������lambda���ʽ��Ϊ����
        noEmpty.ifPresent(li -> System.out.println(li));

        // ��ֵ���䷵�أ����򷵻�ָ��������ֵ
        List<String> a = noEmpty.orElse(Arrays.asList("33"));
        // ���Խ���Supplier�ӿڵ�ʵ����������Ĭ��ֵ
        noEmpty.orElseGet(() -> Arrays.asList("44"));
        try {
            noEmpty.orElseThrow(Exception::new);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // map����
        noEmpty.map(li -> li.add("3"));

        // ����
        noEmpty.filter(li -> li.size() > 3);



    }


}
