package com.wrh.spring.custom.service;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author wuruohong
 * @Classname User
 * @Description TODO
 * @Date 2022/2/22 15:43
 */
@Order(2)
@Service
public class User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void test() {
        System.out.println("this is user");
    }

    @Bean
    public Benz getBenz() {
        return new Benz();
    }
}
