package com.wrh.fangshua;

import com.wrh.redis.RedisTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:57 2019/12/4 0004
 * @Modified By:
 */
public class FangshuaInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    RedisTools redisTools;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod){

            HandlerMethod hm = (HandlerMethod) handler;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if(accessLimit == null) {
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean login = accessLimit.needLogin();
            String key = request.getRequestURI();

            if(login){
                key += "" + "1";
            }

//            AccessKey
        }
        return true;
    }
}
