package com.wrh.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:58 2019/11/25 0025
 * @Modified By:
 */
@Aspect
public class TestAspectJ {
    @Pointcut("execution(* *.say(..))")
    public void test(){}

    @Before("test()")
    public void before(){
        System.out.println("before test..");
    }

    @After("test()")
    public void after(){
        System.out.println("after test..");
    }

    @Around("test()")
    public Object around(ProceedingJoinPoint p){
        System.out.println("before1");
        Object o = null;
        try {
            o = p.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("after1");
        return o;
    }
}
