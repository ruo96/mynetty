package com.wrh.aspect;

import com.alibaba.fastjson.JSON;
import com.wrh.annotate.annotation.NeedDatav;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author wuruohong
 * @Classname DatavCheckAspect
 * @Description TODO
 * @Date 2020/8/31 14:43
 */
@Slf4j
@Aspect
@Component
public class DatavCheckAspect {

    /**
     * 第一个*代表返回类型不限  第二个*代表所有类  如果有第三个*代表所有方法   (..)代表参数不限**
     * @Around→@Before→@Around→@After执行 ProceedingJoinPoint.proceed() 之后的操作→@AfterRunning(如果有异常→@AfterThrowing)
     */
    @Pointcut(value = "execution(public * com.wrh.controller.ApiController.*(..))")
    public void allMethods() {
    }

    @Around(value = "allMethods() && (args(request,..))")
    public String around(ProceedingJoinPoint joinPoint, HttpServletRequest request) throws Throwable{

        log.info("[aspect]>>> api aspect begin");

        long start = System.currentTimeMillis();
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("[aspect]>>> Request Params : {} uri: {}", JSON.toJSONString(request.getParameterMap()) , request.getRequestURL());


        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        /*Annotation[] annotations = targetMethod.getAnnotations();
        for (Annotation annotation : annotations) {
            //此处可以改成自定义的注解
            if (annotation.annotationType().equals(NeedDatav.class)) {
                log.info("[aspect]>>> api has datav");
            }
        }*/

        NeedDatav needDatav = targetMethod.getAnnotation(NeedDatav.class);
        if(needDatav != null){
            log.info("[aspect]>>> api has datav");
            String token = request.getParameter("token");
            if(StringUtils.isBlank(token)){
                return "token empty";
            }
            if(!token.equals("123456")){
                return "wrong token";
            }
        }


        String token = request.getParameter("token");
        log.info("[aspect]>>> token:{}",token);

        String result = (String) joinPoint.proceed();

        log.info("[aspect]>>> Cost : {} ms", System.currentTimeMillis() - start);
        return result;
    }

    @After("within(com.wrh.controller.*Controller)")
    public void after() {
        System.out.println("controller 方法执行完之后的步骤 aop");
    }

    @AfterThrowing("within(com.wrh.controller.*Controller)")
    public void afterThrow() {
        System.out.println("controller 方法执行完之后 抛出异常后的步骤 aop");
    }

}
