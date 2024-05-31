package com.fang.doit.design.limiter;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.design.limiter
 * @Description:
 * @date Date : 2024-05-31 11:52 上午
 */
import java.util.concurrent.*;

public class TokenBucketLimiter {

    /**
     * 令牌桶限流：按照一定的速率向固定大小的桶加令牌，然后以请求的速率从桶内取走，直到桶里没令牌（允许一次可请求多个）
     */

    /**
     * 桶的容量
     */
    private long capacity;
    /**
     * 当前令牌数量
     */
    private long tokens;
    /**
     * 令牌填充间隔时间（纳秒）
     */
    private long refillInterval;
    /**
     * 每次填充的令牌数量
     */
    private long refillAmount;
    /**
     * 令牌桶开始填充的时间戳（纳秒）
     */
    private long startTime;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public TokenBucketLimiter(long capacity, long refillInterval, long refillAmount) {
        this.capacity = capacity;
        this.tokens = capacity;
        this.refillInterval = refillInterval;
        this.refillAmount = refillAmount;
        this.startTime = System.nanoTime();
        // 固定速率向桶内加入令牌
        scheduler.scheduleAtFixedRate(this::refillTokens, refillInterval, refillInterval, TimeUnit.NANOSECONDS);
    }

    private synchronized void refillTokens() {
        long currentTime = System.nanoTime();
        long elapsedTime = currentTime - startTime;
        long tokensToAdd = (elapsedTime / refillInterval) * refillAmount;
        tokens = Math.min(tokens + tokensToAdd, capacity);
        startTime = currentTime;
    }

    public synchronized boolean tryConsume(long amount) {
        if (tokens >= amount) {
            tokens -= amount;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TokenBucketLimiter limiter = new TokenBucketLimiter(100, TimeUnit.SECONDS.toNanos(1), 10);

        // 模拟请求处理
        for (int i = 0; i < 10; i++) {
            boolean canConsume = limiter.tryConsume(30);
            if (canConsume) {
                System.out.println("Request " + i + " is allowed.");
                // 模拟请求处理逻辑
            } else {
                System.out.println("Request " + i + " is rejected.");
            }
        }
    }
}
