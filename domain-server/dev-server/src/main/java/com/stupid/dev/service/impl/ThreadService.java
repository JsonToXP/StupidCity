package com.stupid.dev.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {

    @Async("commonTaskExecutor")
    public void getStrAsync(){
        System.out.println(Thread.currentThread().getName()+"_ok");
    }

}
