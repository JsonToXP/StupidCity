package com.stupid.dev.infrastructure.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Controller日志打印
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class ControllerLogAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(* com.stupid.dev.controller.*.*(..))")
    public void pointcut(){}

    /**
     * 切面
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        // 获取URI中的变量参数
        //Map uriParamMap = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        log.info("\n\n" +
                        "[======================CONTROLLER LOG===========================]\n" +
                        "[URL] : {}\n" +
                        "[IP] : {}\n" +
                        "[方法类型] : {}\n" +
                        "[包路径] : {}\n" +
                        "[类名] : {}\n" +
                        "[请求参数] : {}\n" +
                        "[===============================================================]\n",
                request.getRequestURL().toString(),
                request.getRemoteAddr(),
                request.getMethod(),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));

    }
}
