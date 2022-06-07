package com.stupid.demo.future;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 缺点
 * get()方法阻塞
 * isDone()无法确定结束时间
 */
public class FutureDemo {

    public static void main(String[] args) {

        String name = Thread.currentThread().getName();
        System.out.println(name);
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            try {
                String name1 = Thread.currentThread().getName();
                System.out.println(name1);
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.getMessage();
            }
            return "ok";
        });
        Thread thread = new Thread(futureTask,"t1");
        thread.start();
        try{
            //String msg = futureTask.get();
            //String msg = futureTask.get(2, TimeUnit.SECONDS);
            while (true){
                boolean done = futureTask.isDone();
                if(!done){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("no done");
                }else {
                    String msg = futureTask.get();
                    System.out.println(msg);
                    break;
                }
            }

        }catch (Exception e){
            e.getMessage();
        }

    }
}
