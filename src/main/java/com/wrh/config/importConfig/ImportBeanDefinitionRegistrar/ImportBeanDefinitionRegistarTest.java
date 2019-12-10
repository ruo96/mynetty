package com.wrh.config.importConfig.ImportBeanDefinitionRegistrar;

import com.wrh.config.importConfig.ImportSelectorTest.Triangle;
import com.wrh.config.importConfig.importTest.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:28 2019/12/2 0002
 * @Modified By:
 */
public class ImportBeanDefinitionRegistarTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfigThree.class);
        Circle circle = context.getBean(Circle.class);
        Triangle triangle = context.getBean(Triangle.class);
        Rectanagle rectanagle = context.getBean(Rectanagle.class);
        circle.sayHi();
        triangle.sayHi();
        rectanagle.sayHi();
    }
}
