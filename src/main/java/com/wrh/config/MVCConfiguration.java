package com.wrh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

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
@Configuration
public class MVCConfiguration implements WebMvcConfigurer {
    /**
     * 这里有个坑，就是要redirect
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("redirect:/internationalize.html");
//        registry.addViewController("/").setViewName("redirect:/op.jpg");
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //如下配置则能可以访问src/main/resources/mysource下面的文件
        registry.addResourceHandler("/pic/**").addResourceLocations("classpath:/static/pic/");
        //如访问mysource文件夹下的a.jpg，则输入：localhost:8080/myprofix/a.jpg
        registry.addResourceHandler("/mypic/**").addResourceLocations("file:E:/mypic/");
    }

    /**
     * 跨域解决方式 4种   1，@CrossOrigin 加到类上或者方法上  2 实现 addCorsMappings   3. 注册bean
     */
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:8081")
                .maxAge(1800);
    }*/

//    @Bean
    CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.addAllowedOrigin("http://localhost:8081");
        cfg.addAllowedMethod("*");
        source.registerCorsConfiguration("/**",cfg);
        return new CorsFilter(source);
    }
}
