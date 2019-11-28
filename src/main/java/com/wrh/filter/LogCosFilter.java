package com.wrh.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:23 2019/11/11 0011
 * @Modified By:
 */
@Slf4j
public class LogCosFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();

        filterChain.doFilter(servletRequest,servletResponse);

        log.info("cost time: {}",System.currentTimeMillis() - start);
    }

    @Override
    public void destroy() {

    }
}
