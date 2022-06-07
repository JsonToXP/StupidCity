package com.stupid.demo.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 回调通知
 * 异步任务
 * 多个异步任务之间有依赖关系
 * 多个异步任务获取最先处理完的任务
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(3);

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // 默认使用ForkJoinPool 线程池
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.getMessage();
            }
        },pool);

        CompletableFuture<String> fu2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.getMessage();
            }
            return "ok";
        });

        try{
            future.get();
            String msg = fu2.get();
            System.out.println(msg);
            pool.shutdown();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
