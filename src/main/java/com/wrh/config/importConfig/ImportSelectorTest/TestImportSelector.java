package com.wrh.config.importConfig.ImportSelectorTest;

import com.wrh.config.importConfig.importTest.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:25 2019/12/2 0002
 * @Modified By:
 */
public class TestImportSelector {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfigTwo.class);
        Circle circle = context.getBean(Circle.class);
        Triangle triangle = context.getBean(Triangle.class);
        circle.sayHi();
        triangle.sayHi();
    }
}
