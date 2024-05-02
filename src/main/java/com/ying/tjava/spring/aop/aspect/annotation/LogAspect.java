package com.ying.tjava.spring.aop.aspect.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Around("@annotation(log)")
    public Object doLog(ProceedingJoinPoint pjp, Log log) throws Throwable {
        System.out.println("[Log] start logging...");
        Object rt = pjp.proceed();
        System.out.println("[Log] end logging.");
        return rt;
    }
}
