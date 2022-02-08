package com.wrh.bean;

import org.springframework.context.annotation.ComponentScan;

/**
 * @author wuruohong
 * @Classname AppConfig
 * @Description TODO
 * @Date 2021/10/8 11:43
 */
//@Component
    @ComponentScan("com.wrh.bean")
public class AppConfig {
    private String name;
    private Integer age;
}
