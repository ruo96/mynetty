package com.wrh.config;

import com.wrh.interceptor.MyInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:46 2019/11/11 0011
 * @Modified By:
 */
@Slf4j
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info(">>>  拦截器启动");
        registry.addInterceptor(myInterceptor);
    }
}
