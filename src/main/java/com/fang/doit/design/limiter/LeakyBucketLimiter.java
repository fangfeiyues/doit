package com.fang.doit.design.limiter;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.design.limiter
 * @Description:
 * @date Date : 2024-05-31 11:54 上午
 */
import java.util.concurrent.*;

public class LeakyBucketLimiter {

    /**
     * 漏斗限流：按照请求的速率向固定大小的桶加水，然后以固定的速率从桶内流出，直到桶里没水（一次只能请求特定数量）
     * -- 但会有并发问题
     */

    /**
     * 桶的容量
     */
    private long capacity;

    /**
     * 桶内请求数
     */
    private long tokens;

    /**
     * 漏桶漏出间隔时间（纳秒）
     */
    private long leakInterval;
    /**
     * 每次漏出的令牌数量
     */
    private long leakAmount;
    /**
     * 漏桶开始漏出的时间戳（纳秒）
     */
    private long startTime;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public LeakyBucketLimiter(long capacity, long leakInterval, long leakAmount) {
        this.capacity = capacity;
        this.leakInterval = leakInterval;
        this.leakAmount = leakAmount;
        this.startTime = System.nanoTime();
        scheduler.scheduleAtFixedRate(this::leakTokens, leakInterval, leakInterval, TimeUnit.NANOSECONDS);
    }

    private synchronized void leakTokens() {
        long currentTime = System.nanoTime();
        long elapsedTime = currentTime - startTime;
        // 定时速率消耗桶内的请求
        long tokensToLeak = (elapsedTime / leakInterval) * leakAmount;
        tokens = Math.max(tokens - tokensToLeak, 0);
        startTime = currentTime;
    }

    public synchronized boolean tryConsume(long amount) {
        // 请求加入后是否大于桶容量
        if (amount + tokens >= capacity) {
            tokens += amount;
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        LeakyBucketLimiter limiter = new LeakyBucketLimiter(100, TimeUnit.SECONDS.toNanos(1), 10);

        // 模拟请求处理
        for (int i = 0; i < 10; i++) {
            boolean canConsume = limiter.tryConsume(1);
            if (canConsume) {
                System.out.println("Request " + i + " is allowed.");
                // 模拟请求处理逻辑
            } else {
                System.out.println("Request " + i + " is rejected.");
            }
        }
    }
}
