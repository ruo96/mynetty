package com.wrh.interceptor;

import com.wrh.annotate.annotation.Passport;
import com.wrh.annotate.annotation.PassportTotal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:42 2019/11/11 0011
 * @Modified By:
 */
@Slf4j
@Component
public class MyInterceptor extends HandlerInterceptorAdapter {
    long start =  System.currentTimeMillis();
    @Override
    public  boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)  throws  Exception  {
        log.info("===> interceptor preHandle 启动");
        start =  System.currentTimeMillis();

        if(o instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod)o;
            Passport passport = h.getMethod().getAnnotation(Passport.class);
            if( !Objects.isNull(passport) ) {
                log.info("===> 用户想执行的操作带有passport注解 YES！");
            } else {
                log.info("===> 用户想执行的操作不带有passport注解 NO！");
            }
            //判断后执行操作...
            PassportTotal passportTotal = h.getMethod().getDeclaringClass().getAnnotation(PassportTotal.class);
            if( !Objects.isNull(passportTotal) ) {
                log.info("===> 用户想执行的操作 controller 带有passportTotal注解 YES！");
            } else {
                log.info("===> 用户想执行的操作 controller 不带有passportTotal注解 NO！");
            }
        }

        return  true;
    }
    @Override
    public  void postHandle(HttpServletRequest httpServletRequest,  HttpServletResponse httpServletResponse,  Object o,  ModelAndView modelAndView)  throws  Exception  {
        log.info("Interceptor 调用 cost={}",(System.currentTimeMillis()-start));
    }
    @Override
    public  void afterCompletion(HttpServletRequest httpServletRequest,  HttpServletResponse httpServletResponse,  Object o,  Exception e)  throws  Exception  {
    }
}
