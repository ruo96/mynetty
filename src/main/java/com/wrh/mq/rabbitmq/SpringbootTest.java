package com.wrh.mq.rabbitmq;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.Cookie;

/**
 * @author wuruohong
 * @Classname SpringbootTest
 * @Description TODO
 * @Date 2020/11/27 16:52
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest {
    public static final String EXCHANGE = "springboot_exchange";
    public static final String ROUTING_KEY = "springboot_routing_key";

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void Test22() {
        /** exchange, routingkey, message*/
        this.amqpTemplate.convertAndSend(EXCHANGE,ROUTING_KEY,"springboot first mq message");
        Cookie cookie = new Cookie("msg","hello");

    }
}
