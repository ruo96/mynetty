package com.wrh.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * @Created by wrh
 * @Description: 这个过滤器是采用代码配置的方式注册的，还有可以通过注解配置的
 * @Date: Created in 下午 2:23 2019/11/11 0011
 * @Modified By:
 */
@Slf4j
public class LogCosFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("----------------------->过滤器 1 被创建");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();


        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponseWrapper resp = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);

        String requestURI = req.getRequestURI();
        log.info("--------------------->过滤器：请求地址"+requestURI); //记录请求
        if(requestURI.contains("info")){
            // servletRequest.getRequestDispatcher("/failed").forward(servletRequest, servletResponse);
            log.info("过滤器准备拦截");
//            resp.sendRedirect("/failed");
        }else{
//            filterChain.doFilter(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest,servletResponse);

        log.info("cost time: {}",System.currentTimeMillis() - start);
    }

    @Override
    public void destroy() {
        log.info("----------------------->过滤器被销毁");
    }
}
