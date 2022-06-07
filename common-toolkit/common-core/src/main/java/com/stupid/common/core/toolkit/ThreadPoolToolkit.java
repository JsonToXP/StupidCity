package com.stupid.common.core.toolkit;

import java.util.concurrent.*;

/**
 * 核心线程数
 * 最大线程数
 * 最大空闲时间
 * 时间单位
 * 任务队列
 * 线程工厂
 * 拒绝策略
 */
public class ThreadPoolToolkit {

    /**
     * 核心线程数
     */
    private static final int corePoolSize = 10;

    /**
     * 最大线程数
     */
    private static final int maximumPoolSize = 30;

    /**
     * 最大空闲时间
     */
    private static final long keepAliveTime = 100L;

    /**
     * 时间单位
     */
    private static final TimeUnit unit = TimeUnit.MILLISECONDS;

    /**
     * 任务队列
     */
    private static final BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(10);

    /**
     * 线程工厂
     */
    private static final ThreadFactory factory = ThreadPoolToolkit::getThread;


    /**
     * 拒绝策略
     */
    private static final ThreadPoolExecutor.AbortPolicy policy = new ThreadPoolExecutor.AbortPolicy();

    /**
     * 创建线程池
     */
    public static ExecutorService executorService = new ThreadPoolExecutor(corePoolSize
            , maximumPoolSize
            , keepAliveTime, unit
            , queue
            , factory
            , policy);


    /**
     * 创建线程
     */
    private static Thread getThread(Runnable r){
        String preName = SpringContextToolkit.getProperty("spring.application.name");
        StringBuffer buffer = new StringBuffer(preName);
        buffer.append("_pool_").append(System.currentTimeMillis());
        return new Thread(r,buffer.toString());
    }
}
