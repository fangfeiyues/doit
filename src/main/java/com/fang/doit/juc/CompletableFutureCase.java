package com.fang.doit.juc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/4/24 19:22
 */
public class CompletableFutureCase {

    public void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted exception");
        }
    }

    public void run_async(){
        // 任务 1：洗水壶 -> 烧开水
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("T1: 洗水壶...");
                    sleep(1, TimeUnit.SECONDS);
                    System.out.println("T1: 烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });


        // 任务 2：洗茶壶 -> 洗茶杯 -> 拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("T2: 洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2: 洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2: 拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return " 龙井 ";
                });

        // 任务 3：任务 1 和任务 2 完成后执行：泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2, (___, tf) -> {
                    System.out.println("T1: 拿到茶叶:" + tf);
                    System.out.println("T1: 泡茶...");
                    return " 上茶:" + tf;
                });

        // 等待任务 3 执行结果
        System.out.println(f3.join());

    }


    public void run_sum() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<CompletableFuture<Integer>> futures = nums.stream().map(value -> CompletableFuture.supplyAsync(() -> {
            // 提交到线程池做一些异步的操作
            sleep(value,TimeUnit.SECONDS);
            return value;
        })).collect(Collectors.toList());

        CompletableFuture<Integer> sumFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).thenApplyAsync(v -> {
            // 所有异步结束后汇总
            return futures.stream().mapToInt(CompletableFuture::join).sum();
        });
        System.out.println(System.currentTimeMillis() + " 等待结果中...");
        System.out.println(System.currentTimeMillis() + " 结果出来..." + sumFuture.join());
    }


    public static void main(String[] args) {
        CompletableFutureCase completableFutureCase = new CompletableFutureCase();
        completableFutureCase.run_sum();
    }
}
