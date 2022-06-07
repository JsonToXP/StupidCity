package com.stupid.demo.future;

import java.util.concurrent.*;

public class CompletableFutureDemo2 {

    public static void main(String[] args) throws Exception{

        ExecutorService pool = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+"-----运行中");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.getMessage();
            }
            if(result > 5){
                int a = 0/0;
            }
            return result;
        },pool).whenComplete((v,e) -> {
            // todo 异步任务执行完成后，会自动调用回调方法
            if(e==null){
                System.out.println("result = "+v);
            }
        }).exceptionally(e -> {
            // todo 当异步任务报错时，会调用该方法
            System.out.println(e.getMessage());
            return 0;
        });

        System.out.println(Thread.currentThread().getName()+"-----运行中");

        // todo 当主线程执行完毕后，默认使用的ForkJoinPool会关闭
        try{

        }finally {
            pool.shutdown();
        }

    }
}
