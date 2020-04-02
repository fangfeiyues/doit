package com.fang.doit.lock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author by Feiyue on 2020/1/22 11:21 AM
 */
public class CompletableFutureV2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {

        });

        // 然后执行下一个 不需要上一个结果
        f1.thenRun(() -> {

        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {

            return "nice";
        });


        // 执行下一个 需要上一个异步结果
        f2.thenAccept(a -> {
            System.out.println(a);
        });

        CompletableFuture<String> f2_1 = f2.thenApply(a -> {
            return a + " you";
        });

        // thenCompose方法连同thenApply有着同样的实现结果的合并运算。但是他们内部实现却是不一样的类似于map和flatMap
        // 但是thenCompose 是来连接两个CompletableFuture
        f2_1.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " compose"));
        System.out.println(f2_1.get());


        // 并行多个任务


    }

}
