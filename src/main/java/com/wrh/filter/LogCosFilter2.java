package com.wrh.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Created by wrh
 * @Description: 这个是通过注解来生效的
 * @Date: Created in 下午 2:23 2019/11/11 0011
 * @Modified By:
 */
@Slf4j
@WebFilter(urlPatterns =  "/*", filterName =  "logFilter2")
public class LogCosFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("----------------------->过滤器 2 被创建");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();

        filterChain.doFilter(servletRequest,servletResponse);

        log.info("filter2 cost time: {}",System.currentTimeMillis() - start);
    }

    @Override
    public void destroy() {

    }
}
