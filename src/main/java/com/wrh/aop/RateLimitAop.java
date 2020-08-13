package com.wrh.aop;

import com.google.common.util.concurrent.RateLimiter;
import com.wrh.annotate.annotation.ConcurrentRateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author wuruohong
 * @Classname RateLimitAop
 * @Description TODO
 * @Date 2020/7/10 15:22
 */
@Slf4j
@Aspect
@Component
public class RateLimitAop {
    RateLimiter r = RateLimiter.create(0.2);

    @Pointcut(value = "execution(public * com.wrh.controller.service.HelloService.*(..))")
    public void allMethods() {
    }

    @Around(value = "allMethods()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method proxyMethod = methodSignature.getMethod();
        String methodName = proxyMethod.getName();

        ConcurrentRateLimiter limiter = proxyMethod.getAnnotation(ConcurrentRateLimiter.class);
        if(!Objects.isNull(limiter)){
            log.info("[limiter]>>> method:{}  cause limit", methodName);
            r.acquire();
        }
        joinPoint.proceed();
    }
}
