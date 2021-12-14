package com.wrh.bean.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuruohong
 * @Classname AuthorConfig
 * @Description TODO
 * @Date 2021/12/14 14:48
 */
@Configuration
public class AuthorConfig {

    @Bean(initMethod = "beanInit")
    public Author author() {
        Author author = new Author();
        author.setName("Tom");
        author.setAddress("中国");
        author.setAge(18);

        return author;
    }
}
