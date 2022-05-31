package com.stupid.dev.infrastructure.toolkit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class SpringToolkit implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    /**
     * 获取BeanFactory
     * 通过set方法，将BeanFactory注入
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringToolkit.beanFactory = beanFactory;
    }

    /**
     * 从Bean工厂中获取指定对象
     */
    public static <T> T getBean(Class c){
        return (T) beanFactory.getBean(c);
    }
}
