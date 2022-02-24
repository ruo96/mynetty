package com.wrh.spring.custom.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wuruohong
 * @Classname UserSerive
 * @Description TODO
 * @Date 2022/2/22 14:58
 */
@Component
public class UserService {

    @Value("wrh")
    private User user;

    public UserService() {
    }

    public void test(){
        System.out.println("usersevice");
    }
}
