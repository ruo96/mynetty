package com.wrh.controller.mq.service.impl;

import com.wrh.controller.mq.service.RabbitmqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author wuruohong
 * @Classname RabbitmqServiceImpl
 * @Description TODO
 * @Date 2021/3/29 16:36
 */
@Slf4j
@Service
public class RabbitmqServiceImpl implements RabbitmqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendDelayedMessage(String queueName, String msg) {
        LocalDateTime now = LocalDateTime.now();
        log.info(">>> rabbitmq 发送时间： {}", now.toString());
        rabbitTemplate.convertAndSend("test_exchange", queueName, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
//                message.getMessageProperties().setHeader("x-delay",3000);
                message.getMessageProperties().setDelay(3000);
                return message;
            }
        });

    }
}
