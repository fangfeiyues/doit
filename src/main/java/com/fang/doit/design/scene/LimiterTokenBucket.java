package com.fang.doit.design.scene;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LimiterTokenBucket {
    // 桶中当前令牌数量
    private AtomicInteger tokenCount;
    // 桶的最大容量
    private int capacity;
    // 令牌生成速率（每秒生成的令牌数）
    private double ratePerSecond;
    // 上一次生成令牌的时间戳
    private long lastRefillTimestamp;

    public LimiterTokenBucket(int capacity, double ratePerSecond) {
        this.capacity = capacity;
        this.ratePerSecond = ratePerSecond;
        this.tokenCount = new AtomicInteger(0);
        this.lastRefillTimestamp = System.currentTimeMillis();

        // 启动定时任务，定期向桶中添加令牌
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::refill, 0, 100, TimeUnit.MILLISECONDS);
    }

    // 定期向桶中添加令牌
    private void refill() {
        long now = System.currentTimeMillis();
        long elapsedMillis = now - lastRefillTimestamp;
        lastRefillTimestamp = now;

        // 计算新增令牌数，注意避免超过桶的容量
        int newTokens = (int) (elapsedMillis * ratePerSecond / 1000.0);
        int currentTokens = tokenCount.get();
        int tokensToAdd = Math.min(newTokens, capacity - currentTokens);
        tokenCount.addAndGet(tokensToAdd);
    }

    // 尝试获取令牌
    public boolean tryAcquire() {
        while (true) {
            int currentTokens = tokenCount.get();
            if (currentTokens <= 0) {
                return false;
            }
            if (tokenCount.compareAndSet(currentTokens, currentTokens - 1)) {
                return true;
            }
        }
    }

    // 动态调整限流规则
    public synchronized void updateRateLimit(int newCapacity, double newRatePerSecond) {
        this.capacity = newCapacity;
        this.ratePerSecond = newRatePerSecond;
        // 重置桶中令牌数量
        tokenCount.set(0);
        lastRefillTimestamp = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        LimiterTokenBucket limiter = new LimiterTokenBucket(10, 5.0);

        // 模拟请求
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                while (true) {
                    if (limiter.tryAcquire()) {
                        System.out.println("Request allowed: " + Thread.currentThread().getName());
                    } else {
                        System.out.println("Request rejected: " + Thread.currentThread().getName());
                    }
                    try {
                        // 控制请求频率，避免过于频繁的输出影响观察效果
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        // 模拟动态调整限流规则
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                limiter.updateRateLimit(5, 2.0);
                System.out.println("Rate limit updated to capacity:5, rate:2.0");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}