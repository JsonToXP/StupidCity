package com.stupid.common.core.config;

import com.stupid.common.core.toolkit.SpringContextToolkit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

import static com.stupid.common.core.toolkit.OSToolkit.getProcessors;

/**
 * 开启线程池
 * CPU密集型 CPU核心数 + 1 Ex:for循环
 * IO密集型 CPU核心数 * 2 Ex:IO操作多
 * 实际还是要压测判断
 *
 * 如何判断
 * 1、一般平时cpu使用率4%以下，都是IO密集型，IO密集型核心线程数设置大小具体看实践，目前项目里核心线程数设置50，最大线程数可以和核心线程数相同，队列配置大一些，使永远触发不到最大线程数
 * 2、如果是大量计算CPU使用率过高，属于CPU密集型，CPU密集型以4C8G为例，核心线程数一般设置4，最大线程数可以和核心线程数相同，队列配置大一些，使永远触发不到最大线程数
 *
 * 默认情况下，keep-alive 策略仅适用于超过 corePoolSize 线程的情况，没有任务会进行空跑， 和线程池生命周期一样， 除非线程池shutdown；
 * 但是方法allowCoreThreadTimeOut(boolean)也可用于将此超时策略应用于核心线程，只要 keepAliveTime 值不为零即可
 *
 * @Async 成功条件
 * 1、启动项加@EnableAsync注解
 * 2、需要异步的方法不能和调用方法在一个类中
 * 3、返回值为void或future
 *
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    /**
     * 获取当前服务器CPU核数
     */
    private static final int processors = getProcessors();

    /**
     * 核心线程数
     */
    private static final int corePoolSize = processors;

    /**
     * 最大线程数
     */
    private static final int maximumPoolSize = corePoolSize;

    /**
     * 最大空闲时间
     */
    private static final int keepAliveTime = 60;

    /**
     * 队列大小
     */
    private static final int queueCapacity = Integer.MAX_VALUE;

    private SpringContextToolkit springContextToolkit;

    @Autowired
    public void setSpringContextToolkit(SpringContextToolkit springContextToolkit) {
        this.springContextToolkit = springContextToolkit;
    }

    @Bean("commonTaskExecutor")
    public Executor asyncServiceExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 最大线程数
        executor.setMaxPoolSize(maximumPoolSize);
        // 最大活跃时间
        executor.setKeepAliveSeconds(keepAliveTime);
        // 队列大小
        executor.setQueueCapacity(queueCapacity);
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 线程名称
        executor.setThreadNamePrefix(SpringContextToolkit.getProperty("spring.application.name")+"_");
        // 执行初始化
        executor.initialize();
        return executor;
    }
}
