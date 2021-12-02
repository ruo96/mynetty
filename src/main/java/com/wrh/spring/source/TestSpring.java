package com.wrh.spring.source;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wuruohong
 * @Classname TestSpring
 * @Description TODO
 * @Date 2021/12/2 11:53
 */
public class TestSpring {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        //ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("spring.xml");
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(UserService.class);
        beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(1,new OrderService());
        beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
        context.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) context.getBean("userService");
        userService.test();

    }
}
