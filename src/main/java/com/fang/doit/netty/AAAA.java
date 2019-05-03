package com.fang.doit.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ListSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/4/18 17:47
 */
public class AAAA {

    private final List<String> list = new ArrayList<String>();

    public void add() {
        list.add("1");
    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10000);
        AAAA a = new AAAA();
        for (int i = 0; i < 10000; i++) {
            new Thread() {
                @Override
                public void run() {
                    a.add();
                    countDownLatch.countDown();
                }
            }.start();
        }
        countDownLatch.await();
        System.out.println(JSON.toJSONString(a.list.size()));
    }
}
