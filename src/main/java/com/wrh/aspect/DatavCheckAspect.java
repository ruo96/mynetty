package com.wrh.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wrh.annotate.annotation.NeedDatav;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

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

}
