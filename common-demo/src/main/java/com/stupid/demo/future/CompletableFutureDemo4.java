package com.stupid.demo.future;

import com.stupid.common.core.toolkit.ThreadPoolToolkit;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 多个运行任务之间的依赖关系
 */
public class CompletableFutureDemo4 {

    public static void main(String[] args) {

        ExecutorService pool = ThreadPoolToolkit.executorService;

        // todo thenApply 下一步依赖上一步的返回值，且有返回
        /**
         * thenApply 和 thenApplyAsync
         * thenApply 由谁来调度不确定，可能是main、可能是默认的ForkJoinPool或指定的Pool
         * thenApplyAsync 的调度取决于指定的Pool
         *
         * 如果 thenApply 抢到了main线程并阻塞，则整个流程阻塞
         */
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.getMessage();
            }
            System.out.println(Thread.currentThread().getName()+" thenApply step1===========>");
            return 1;
        },pool).thenApplyAsync(v1 -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.getMessage();
            }
            System.out.println(Thread.currentThread().getName()+" thenApply step2===========>");
            return v1 + 2;
        },pool).thenApply(v2 -> {
            System.out.println(Thread.currentThread().getName()+" thenApply step3===========>");
            return v2 + 3;
        }).whenComplete((v,e) -> {
            if(e==null){
                System.out.println(Thread.currentThread().getName()+" result = " + v);
            }
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return 0;
        });

        // todo thenApply 下一步依赖上一步的返回值，且有返回，并能处理异常
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.getMessage();
            }
            System.out.println(Thread.currentThread().getName()+" handle step1===========>");
            return 1;
        },pool).handle((v1,e) -> {
            System.out.println(Thread.currentThread().getName()+" handle step2===========>");
            return v1 + 2;
        }).handle((v2,e) -> {
            System.out.println(Thread.currentThread().getName()+" handle step3===========>");
            return v2 + 3;
        }).whenComplete((v,e) -> {
            if(e==null){
                System.out.println(Thread.currentThread().getName()+" handle result = " + v);
            }
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return 0;
        });

        // todo thenApply 下一步依赖上一步的返回值，且没有返回
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.getMessage();
            }
            System.out.println(Thread.currentThread().getName()+" step1===========>");
            return 1;
        },pool).thenAccept(v1 -> {
            System.out.println(Thread.currentThread().getName()+" step2===========>"+v1);
        }).thenAccept(v2 -> {
            System.out.println(Thread.currentThread().getName()+" step3===========>"+v2);
        }).whenComplete((v,e) -> {
            if(e==null){
                System.out.println(Thread.currentThread().getName()+" result = " + v);
            }
        });

        // todo thenApply 下一步不依赖上一步的返回值，且没有返回
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.getMessage();
            }
            System.out.println(Thread.currentThread().getName() + " run step1===========>");
            return 1;
        }, pool).thenRun(() -> {
            System.out.println(Thread.currentThread().getName() + " run step2===========>");
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName() + " run step3===========>");
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println(Thread.currentThread().getName() + " run result = " + v);
            }
        });

        System.out.println(Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(10);
        }catch (Exception e){
            e.getMessage();
        }
        pool.shutdown();
    }
}
