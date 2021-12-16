package com.wrh.spring.simpleContainer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

/**
 * @author wuruohong
 * @Classname TestSimpleSpringContainer
 * @Description TODO
 * @Date 2021/12/15 14:37
 */
public class TestSimpleSpringContainer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SimpleConfig.class);
        Stream.of(context.getBeanDefinitionNames()).forEach(s-> System.out.println(s));
        System.out.println(context.getBean(Person.class));
    }
}
