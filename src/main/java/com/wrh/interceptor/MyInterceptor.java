package com.wrh.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
