package com.fang.doit.base;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/5/11 16:25
 */
public class FinalCase {

    private final List<String> list = new ArrayList<String>();


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        FinalCase finalCase = new FinalCase();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                finalCase.list.add("a");
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println(JSON.toJSONString(finalCase.list.size()));
    }
}
