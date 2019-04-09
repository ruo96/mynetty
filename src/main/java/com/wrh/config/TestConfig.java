package com.wrh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:59 2019/3/8 0008
 * @Modified By:
 */
public class TestConfig {
    @Autowired
    ZidingyiConfig config;

    public static String user;

    @Value("${user}")
    public void setUser(String user) {
        TestConfig.user = config.getUser();
    }

    public static void main(String[] args) {
        System.out.println(user);
    }
}
