package com.stupid.common.core.toolkit;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
public class SpringContextToolkit implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextToolkit.applicationContext = applicationContext;
    }

    /**
     * 获取上下文对象
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 获取prop配置文件信息
     */
    public static String getProperty(String propertyName){
        ConfigurableEnvironment env = (ConfigurableEnvironment) applicationContext.getEnvironment();
        return env.getProperty(propertyName);
    }

    /**
     * 根据prop前缀获取配置列表
     */
    public static List<String> getPropertyList(String propertyPrefix){

        AtomicReference<List<String>> result = new AtomicReference<>();

        ConfigurableEnvironment env = (ConfigurableEnvironment) applicationContext.getEnvironment();

        env.getPropertySources().stream().forEach(propertySource->{
            if (propertySource instanceof OriginTrackedMapPropertySource) {
                List<String> props = Arrays.asList(((OriginTrackedMapPropertySource) propertySource).getPropertyNames());
                result.set(props.stream().filter(e -> e.startsWith(propertyPrefix)).collect(Collectors.toList()));
            }
        });

        return result.get();
    }

    /**
     * 判断配置是否存在
     */
    public static boolean containProperty(String propertyName,boolean notBlank){

        Object prop =  applicationContext.getEnvironment().getProperty(propertyName);
        if(null==prop){
            return false;
        }else if(notBlank && Strings.isBlank(prop.toString())){
            return false;
        }
        return true;
    }

}
