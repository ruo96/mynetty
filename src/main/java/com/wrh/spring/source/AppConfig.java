package com.wrh.spring.source;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * @author wuruohong
 * @Classname AppConfig
 * @Description TODO
 * @Date 2021/12/2 11:53
 */
@Component
@ComponentScan("com.wrh.spring.source")
@Import(UserService.class)
@Configuration()
public class AppConfig {

    /*@Bean
    public static UserService userService() {   //  factory-bean = appConfig  对应的这个工厂bean   factory-method = userService
                                         //   如果这个方法是static的，那么对应的 class = "com.wrh.spring.source.AppConfig"  factory-method 不变
        System.out.println("bean 1");
        return new UserService();
    }*/

    /** 会覆盖 ， 真实bean是第二个*/
    /*@Bean
    public UserService userService(OrderService orderService) {
        System.out.println("bean 2");
        return new UserService(orderService);
    }*/


    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
}
