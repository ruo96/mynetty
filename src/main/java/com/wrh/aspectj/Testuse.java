package com.wrh.aspectj;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:03 2019/11/25 0025
 * @Modified By:
 */
public class Testuse {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        Person bean2 = (Person)ac.getBean("student");
        bean2.say();
    }
}
