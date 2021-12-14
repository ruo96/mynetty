package com.wrh.spring.source;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author wuruohong
 * @Classname TestSpring
 * @Description TODO
 * @Date 2021/12/2 11:53
 */
public class TestSpring {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();             // springboot用的是这个
        context.register(AppConfig.class);
        context.refresh();
        //AnnotationConfigWebApplicationContext context1 = new AnnotationConfigWebApplicationContext();    // springmvc是用的这个
        //ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("spring.xml");
        //AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        //beanDefinition.setBeanClass(UserService.class);
        //beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(1,new OrderService());
        // 和上面是一样的作用
        //beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(1,new RuntimeBeanReference("orderService"));




        //beanDefinition.setLenientConstructorResolution(false); // 默认true 宽松模式  如果这种模式是不会查太多的，比如不会去看原始类型， 如果是true模式就会查更多
        //beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
        //context.registerBeanDefinition("userService", beanDefinition);

        //UserService userService = (UserService) context.getBean("userService");
        UserService userService = (UserService) context.getBean("com.wrh.spring.source.UserService");
        //Iterator<String> it =  context.getBeanFactory().getBeanNamesIterator();
        String[] it =  context.getBeanFactory().getBeanNamesForType(UserService.class);
        System.out.println("all beanname is :"+Arrays.toString(it));
        //UserService userService = (UserService) context.getBean(UserService.class);  // 这种是根据import注解导入进来的获取方法
        userService.test();

        System.out.println("UserService.class.getName() = " + UserService.class.getName());

        //Object[] objects = new Object[]{new A()};
        //System.out.println(MethodInvoker.getTypeDifferenceWeight(new Class[]{A.class}, objects));
        //System.out.println(MethodInvoker.getTypeDifferenceWeight(new Class[]{B.class}, objects));
        //System.out.println(MethodInvoker.getTypeDifferenceWeight(new Class[]{C.class}, objects));
        //System.out.println(MethodInvoker.getTypeDifferenceWeight(new Class[]{D.class}, objects));

    }

    @Test
    public void Test51() {
        UserService userService = new UserService();
        System.out.println("UserService.class.isAssignableFrom(UserService.class) = " + UserService.class.isAssignableFrom(UserService.class));
        System.out.println("OrderService.class.isAssignableFrom(UserService.class) = " + OrderService.class.isAssignableFrom(UserService.class));

    }
}
