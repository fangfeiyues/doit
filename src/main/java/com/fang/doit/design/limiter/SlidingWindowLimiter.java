package com.fang.doit.design.limiter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.design.limiter
 * @Description:
 * @date Date : 2024-05-31 3:25 下午
 */
public class SlidingWindowLimiter {

    /**
     * 滑动窗口限流：窗口内令牌数没超过阈值，可行
     */

    private Queue<Long> timestamps;

    /**
     * 窗口大小，单位为请求数量
     */
    private int windowSize;

    /**
     * 时间窗口大小，单位为毫秒
     */
    private long interval;

    public SlidingWindowLimiter(int windowSize, long interval) {
        this.windowSize = windowSize;
        this.interval = interval;
        this.timestamps = new LinkedList<>();
    }

    public boolean isAllowed() {
        long now = System.currentTimeMillis();
        // 移除窗口外的时间戳
        while (!timestamps.isEmpty() && (now - timestamps.peek() > interval)) {
            timestamps.poll();
        }
        // 判断当前窗口内的请求是否超过限制
        if (timestamps.size() < windowSize) {
            timestamps.add(now);
            return true;
        }
        return false;
    }




}
