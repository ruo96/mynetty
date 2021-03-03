package com.wrh.bean.beanonclass;

import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuruohong
 * @Classname TestBeanConfiguration
 * @Description TODO
 * @Date 2021/2/20 18:17
 */
@ConditionalOnProperty(prefix = "demo",name="enable", havingValue = "true",matchIfMissing=true )
@Configuration
@Slf4j
public class TestBeanConfiguration {
    @Bean
    public Student a() {
        log.info(">>> because the property , create bean student!!!!!");
        return    new Student();
    }
}
