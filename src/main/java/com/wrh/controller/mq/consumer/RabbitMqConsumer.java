package com.wrh.controller.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author wuruohong
 * @Classname RabbitMqConsumer
 * @Description TODO
 * @Date 2021/3/29 16:41
 */
@Slf4j
//@Component
public class RabbitMqConsumer {

//    @RabbitListener(queues = "test_queue_1")
    public void consumeMsg(String msg) {
        LocalDateTime now = LocalDateTime.now();
        log.info(">>> 接收消息： {}  接收时间：{}", msg,now.toString());
    }
}
