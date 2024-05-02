package com.ying.tjava.spring.aop.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectUtils {

    //  @Before("execution(public * com.ying.tjava.spring.ioc.MailService.*(..))")
    public void doAccessCheck() {
        System.out.println("[Before] do some Access check.");
    }

    //  @Around("execution(public * com.ying.tjava.spring.ioc.UserService.*(..))")
    public Object startTransaction(ProceedingJoinPoint pjp) {
        System.out.println("[Around] start transaction.");
        Object rtValue = null;
        try {
            rtValue = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        System.out.println("[Around] commit transaction.");
        return rtValue;
    }
}
