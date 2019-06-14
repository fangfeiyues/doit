package com.fang.doit.base;

import java.util.LinkedHashMap;

/**
 * created by fang on 2019/6/12/012 22:57
 */
public class LinkedMain {

    public static void main(String[] args) {

        // 会维护键值对的访问顺序
        LinkedHashMap<String, String> map = new LinkedHashMap(10, 0.75f,true);
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.get("2");
        map.forEach((key, value) -> {
            System.out.println(key);
        });

    }
}
