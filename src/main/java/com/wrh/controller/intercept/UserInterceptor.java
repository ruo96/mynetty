package com.wrh.controller.intercept;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author wuruohong
 * @Classname UserInterceptor
 * @Description TODO
 * @Date 2020/8/4 17:06
 */
@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        log.info(">>> UserInterceptor enter");
        InterceptRequestWrapper wrapper;
        if (request instanceof InterceptRequestWrapper) {
            wrapper = (InterceptRequestWrapper) request;
        } else {
            wrapper = new InterceptRequestWrapper(request);
        }
        String body = new String(wrapper.getRequestBodyByte(), UTF_8);
        log.debug(">>> UserInterceptor body {}:", body);

        RequestInfo r = JSON.parseObject(body, RequestInfo.class);
        log.debug(">>> UserInterceptor解析后的报文体 r {}:", r);

        /**
         * 向请求中塞参数
         */
        request.setAttribute("username","interceptor");
        return true;
    }
}
