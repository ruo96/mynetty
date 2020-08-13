package com.wrh.controller.filter;

import com.wrh.controller.wrapper.ParameterRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuruohong
 * @Classname RequestParameterFilter
 * @Description TODO
 * @Date 2020/8/4 16:55
 */
@Slf4j
public class RequestParameterFilter extends OncePerRequestFilter {

    private static final String AUTH_PATH = "/api";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info(">>> RequestParameterFilter enter, request uri: {}", request.getRequestURI());
        log.info(">>> RequestParameterFilter enter, request url: {}", request.getRequestURL());
        /*如果请求路径是为api,进行过滤对参数parameter内容解密，放入request.parameter中*/
        if (request.getRequestURI().indexOf(AUTH_PATH) != -1) {
            /*1.获取加密串,进行解密*/

            /*2.解密出加密串，我和前台约定的是JSON,获取到JSON我将其转换为map，这里我直接用手动封装map代替*/
            Map paramter = new HashMap(16);
            paramter.put("username", "filterName");
            paramter.put("password", "password");
            ParameterRequestWrapper wrapper = new ParameterRequestWrapper(request, paramter);
            filterChain.doFilter(wrapper, response);
            return;
        }else {
            Map paramter = new HashMap(16);
            paramter.put("username", "otherName");
            paramter.put("password", "password");
            ParameterRequestWrapper wrapper = new ParameterRequestWrapper(request, paramter);
            filterChain.doFilter(wrapper, response);
            return;
        }
    }
}
