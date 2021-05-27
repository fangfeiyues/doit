package com.fang.doit.base.lock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author by Feiyue on 2020/1/22 11:21 AM
 */
public class CompletableFutureV2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {

        });

        // Ȼ��ִ����һ�� ����Ҫ��һ�����
        f1.thenRun(() -> {

        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {

            return "nice";
        });


        // ִ����һ�� ��Ҫ��һ���첽���
        f2.thenAccept(a -> {
            System.out.println(a);
        });

        CompletableFuture<String> f2_1 = f2.thenApply(a -> {
            return a + " you";
        });

        // thenCompose������ͬthenApply����ͬ����ʵ�ֽ���ĺϲ����㡣���������ڲ�ʵ��ȴ�ǲ�һ����������map��flatMap
        // ����thenCompose ������������CompletableFuture
        f2_1.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " compose"));
        System.out.println(f2_1.get());


        // ���ж������


    }

}
