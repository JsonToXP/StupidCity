package com.stupid.demo.future;

import com.stupid.common.core.toolkit.ThreadPoolToolkit;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 比较多个任务运行速度 applyToEither
 * 合并多个任务的处理结果 thenCombine
 */
public class CompletableFutureDemo5 {

    public static void main(String[] args) {

        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.getMessage();
            }
            System.out.println(Thread.currentThread().getName());
            return "playA";
        }, ThreadPoolToolkit.executorService);

        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "playB";
        });

        CompletableFuture<String> future = playA.applyToEither(playB, f -> {
            return f + " is win !";
        });

        System.out.println(future.join());

        CompletableFuture<String> planA = CompletableFuture.supplyAsync(() -> {
            return "planA";
        });

        CompletableFuture<String> planB = CompletableFuture.supplyAsync(() -> {
            return "planB";
        });

        CompletableFuture<String> future1 = planA.thenCombine(planB, (a, b) -> {
            return a + b;
        });

        System.out.println(future1.join());
        ThreadPoolToolkit.executorService.shutdown();
    }
}
