package com.wrh.config.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuruohong
 * @Classname QueueConfig
 * @Description TODO
 * @Date 2021/3/29 16:21
 */
//@Configuration
public class QueueConfig {

//    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        CustomExchange customExchange = new CustomExchange("test_exchange","x-delayed-message",true,false,args);
        // 这个设置不设置都没多大关系
//        customExchange.setDelayed(true);
        return customExchange;
    }

//    @Bean
    public Queue queue() {
        Queue queue = new Queue("test_queue_1",true);
        return queue;
    }

//    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(delayExchange()).with("test_queue_1").noargs();
    }
}
