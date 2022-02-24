package com.wrh.spring.custom;


import com.wrh.spring.custom.service.OrderService;
import com.wrh.spring.custom.service.User;
import com.wrh.spring.custom.service.UserService;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.MutablePropertySources;

import java.io.IOException;
import java.util.Map;

/**
 * @author wuruohong
 * @Classname TestSpring
 * @Description TODO
 * @Date 2022/2/22 14:56
 */
public class TestSpring {
    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(OrderService.class);
        beanDefinition.setScope("singleton");
        context.registerBeanDefinition("orderService", beanDefinition);

        UserService service = (UserService) context.getBean("userService");
        service.test();

        OrderService service1 = (OrderService) context.getBean("orderService");

        /**  这种是用来推断构造方法 针对原型情况*/
        //OrderService service1 = (OrderService) context.getBean("orderService","123");
        service1.test();

        /** 注解读取方式*/
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(context);
        annotatedBeanDefinitionReader.register(User.class);
        User user = (User) context.getBean("user");
        user.test();

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory(context);
        User user1 = (User) beanFactory.getBean("user");
        System.out.println("user1 = " + user1);
        user1.test();

        /** xml读取方式 */
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context);
        /** 返回值是load的个数*/
        //xmlBeanDefinitionReader.loadBeanDefinitions("spring.xml");

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
        scanner.scan("com.wrh.spring.custom.service");

        /** 如果不需要那么多功能，也可以直接使用beanfactory来实现*/
        DefaultListableBeanFactory beanFactory1 = new DefaultListableBeanFactory();
        AbstractBeanDefinition beanDefinition1 = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition1.setBeanClass(User.class);
        beanFactory1.registerBeanDefinition("user1",beanDefinition1);

        System.out.println("beanFactory1.getBean(\"user1\") = " + beanFactory1.getBean("user1"));

    //    读取资源
        /*Resource resource =  context.getResource("file://E:\\file\\data.txt");
        System.out.println("resource.contentLength() = " + resource.contentLength());
        File file = resource.getFile();
        List<String> list = FileUtil.readLines(file, "UTF-8");
        System.out.println("list = " + list);

        Resource resource1 =  context.getResource("classpath:content.txt");
        System.out.println("resource1.contentLength() = " + resource1.contentLength());
        File file1 = resource1.getFile();
        List<String> list1 = FileUtil.readLines(file1, "UTF-8");
        System.out.println("list1 = " + list1);

        Resource resource2 =  context.getResource("https://www.baidu.com");
        System.out.println("resource2.contentLength() = " + resource2.contentLength());
        BufferedInputStream inputStream = new BufferedInputStream(resource2.getInputStream());
        byte[] content = new byte[1024];
        inputStream.read(content);
        String str = new String(content);
        System.out.println("str = " + str);*/

        /** 获取系统环境变量*/
        Map<String, Object> map1 = context.getEnvironment().getSystemEnvironment();
        System.out.println("systemEnviroment = " + map1);

        Map<String, Object> map2 = context.getEnvironment().getSystemProperties();
        System.out.println("systemProperties = " + map2);
        System.out.println("map2.get(\"java.security.manager\") = " + map2.get("java.security.manager"));

        /** 如果在类上加了注解  @PropertySource("classpath:spring.properties")  就可以通过下面的方法拿数据  这个里面包含了上面所有的resource*/
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        System.out.println("propertySources = " + propertySources);

        /** 也可以直接从环境变量里面获取*/
        System.out.println("context.getEnvironment().getProperty(\"java.runtime.name\") = " + context.getEnvironment().getProperty("java.runtime.name"));

        /** factorybean拿bean的方法*/
        //context.getBean("wrhFactoryBean"); // 这个是拿到getObject()方法返回的bean
        //context.getBean("&wrhFactoryBean"); // 这个是拿到工厂bean


    }
}
