package com.mlh.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author ltao
 * @Classname AspectTest
 * @description: TODO
 * @date 2019/7/12
 * @Version 1.0
 */
@Component
@Aspect
@Slf4j
public class AopServiceAspect {
    @Pointcut("execution(* com.mlh.aop.AopService.doSomething(..))")
    public void targetMethod(){


    }

    @Around("targetMethod() && args(args)")
    public void around(ProceedingJoinPoint joinPoint,String args){
        Object[] argsArr = joinPoint.getArgs();

        try {
            Object rtv = joinPoint.proceed(argsArr);
            log.info(rtv.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
