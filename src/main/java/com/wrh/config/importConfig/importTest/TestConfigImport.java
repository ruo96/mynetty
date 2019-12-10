package com.wrh.config.importConfig.importTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:19 2019/12/2 0002
 * @Modified By:
 */
public class TestConfigImport {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Circle circle = context.getBean(Circle.class);
        circle.sayHi();
    }
}
