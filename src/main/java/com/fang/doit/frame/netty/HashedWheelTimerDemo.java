package com.fang.doit.frame.netty;

import io.netty.util.HashedWheelTimer;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * created by fang on 2019/4/15/015 23:49
 */
public class HashedWheelTimerDemo {

    public static void main(String[] args) throws Exception {

        /* 创建Timer, 精度为100毫秒
         * tickDuration：每一轮的时间
         * ticksPerWheel：共几轮
         */
        HashedWheelTimer timer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 16);

        System.out.println(LocalTime.now());

        timer.newTimeout((timeout) -> {
            System.out.println(LocalTime.now());
            System.out.println(timeout);
        }, 5, TimeUnit.SECONDS);

        //阻塞main线程
        System.in.read();

        /**
         * 00:00:47.256
         00:00:52.366
         这中间还是有误差？
         */
    }

}
