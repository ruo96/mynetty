package com.wrh.config;

import com.wrh.controller.filter.RequestParameterFilter;
import com.wrh.filter.LogCosFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:27 2019/11/11 0011
 * @Modified By:
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registFilter()  {
        FilterRegistrationBean registration =  new  FilterRegistrationBean();
        registration.setFilter(new LogCosFilter());
        registration.addUrlPatterns("/gogogo");
        registration.setName("LogCostFilter");
        registration.setOrder(1);
        return registration;
    }

//    @Bean
//    public FilterRegistrationBean registFilter1()  {
//        FilterRegistrationBean registration =  new  FilterRegistrationBean();
//        registration.setFilter(new RequestParameterFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("requestFilter");
//        registration.setOrder(2);
//        return registration;
//    }
}
