package com.wrh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author wuruohong
 * @Classname MVCConfiguration
 * @Description 设置默认启动页   https://blog.csdn.net/qq_29477175/article/details/82117177
 * 在spring boot 2.0以后 WebMvcConfigurerAdapter 这个方法已经过时，
 * 通过百度网上的资料之后发现很多人说是改成继承WebMvcConfigurationSupport这个类，
 * 这种方式是有问题的，会导致自动配置失效，应该是实现WebMvcConfigurer这个接口
 * ————————————————
 * 版权声明：本文为CSDN博主「上官云洛1992」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/shangguanyunluo123/article/details/80326857
 * @Date 2020/2/27 10:56
 */
//@Configuration
public class MvcConfigure extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/healthy.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }




}
