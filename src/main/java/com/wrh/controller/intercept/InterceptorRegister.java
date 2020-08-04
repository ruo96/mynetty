package com.wrh.controller.intercept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: wuruohong
 * @Date: 2020/8/4 17:16
 * @Version: 1.0
 * @Description:
 */
@SpringBootConfiguration
public class InterceptorRegister extends WebMvcConfigurerAdapter {
    private static final String SDK_API = "/api/**";
    private static final String SDK_API_EXCLUDE = "/api2/exclude";

    @Autowired
    UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(userInterceptor)
                .addPathPatterns(SDK_API)
                .excludePathPatterns(SDK_API_EXCLUDE);

    }
}
