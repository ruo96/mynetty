package com.wrh.spring.event;

import com.wrh.config.FilterConfig;
import com.wrh.config.MyEventConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author wuruohong
 * @Classname TestEvent
 * @Description TODO
 * @Date 2021/8/31 11:45
 */
public class TestEvent {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyEventConfig.class);
        /** 创建一个applicationEvent对象*/
        EmailEvent event = new EmailEvent("hello", "abc@163.com", "this is a test");
        /** 主动触发该事件*/
        context.publishEvent(event);
    }
}
