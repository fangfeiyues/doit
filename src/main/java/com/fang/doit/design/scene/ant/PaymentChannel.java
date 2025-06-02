package com.fang.doit.design.scene.ant;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class PaymentChannel {

    // 配置线程池
    private static final int MAX_THREADS = 50; // 最大并发数
    private static final ExecutorService executor = new ThreadPoolExecutor(
            10, MAX_THREADS, 30, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    private final RemoteService remoteService = new RemoteService();

    /**
     * 获取可用支付方式列表
     * 1、过滤近期不稳定、心跳失败的
     * 2、随机、权重、最近成功等负载均衡
     * @param paymentMethods 待检查的支付方式
     * @param timeoutMs 总超时时间(毫秒)
     * @return 可用支付方式列表（无序）
     */
    public List<String> getAvailableMethods(List<String> paymentMethods, long timeoutMs) {
        // 1. 创建并发任务
        List<CompletableFuture<Optional<String>>> futures = paymentMethods.stream()
                .map(method -> CompletableFuture.supplyAsync(() -> {
                                    try {
                                        boolean available = remoteService.checkAvailability(method);
                                        return available ? Optional.of(method) : Optional.<String>empty();
                                    } catch (Exception e) {
                                        return Optional.<String>empty(); // 失败视为不可用
                                    }
                                }, executor)
//                                .completeOnTimeout(Optional.empty(), timeoutMs, TimeUnit.MILLISECONDS)
                )
                .collect(Collectors.toList());

        // 2. 等待所有任务完成（或超时）
        CompletableFuture<Void> allDone = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        try {
            allDone.get(timeoutMs, TimeUnit.MILLISECONDS);
        } catch (Exception ignored) {} // 超时后继续处理已完成任务

        // 3. 聚合可用结果
        return futures.stream()
                .filter(CompletableFuture::isDone)
                .map(f -> {
                    try {
                        return f.getNow(Optional.empty());
                    } catch (Exception e) {
                        return Optional.<String>empty();
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    // 支付方式远程服务代理（模拟）
    static class RemoteService {
        // 模拟远程服务调用：随机延迟(0-200ms)和随机可用性
        boolean checkAvailability(String method) throws InterruptedException {
            // 权重、降级、熔断等 LoadBalance
            Thread.sleep(ThreadLocalRandom.current().nextInt(200));
            return ThreadLocalRandom.current().nextBoolean();
        }
    }


    // 示例用法
    public static void main(String[] args) {
        PaymentChannel service = new PaymentChannel();
        List<String> methods = Arrays.asList("余额", "红包", "优惠券", "代金券", "信用卡");

        long start = System.currentTimeMillis();
        List<String> available = service.getAvailableMethods(methods, 150); // 150ms超时
        long cost = System.currentTimeMillis() - start;

        System.out.printf("可用支付方式(%dms): %s%n", cost, available);
    }
}
