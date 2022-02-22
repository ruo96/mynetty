package com.wrh.spring.custom;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author wuruohong
 * @Classname AppConfig
 * @Description TODO
 * @Date 2022/2/22 14:56
 */
@Configuration
@ComponentScan(value = "com.wrh.spring.custom")
public class AppConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        /** 这个就是i18n目录下文件的前缀*/
        messageSource.setBasename("messages");
        return messageSource;

        /** 国际化的使用*/
        //context.getMessage("自定义的展示，就是配置文件的key",null, new Locale("这个里面就是文件后缀"));
    }
}
