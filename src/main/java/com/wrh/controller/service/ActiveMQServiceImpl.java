package com.wrh.controller.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @author wuruohong
 * @Classname ActiveMQServiceImpl
 * @Description TODO
 * @Date 2021/3/3 16:41
 */
@Service
public class ActiveMQServiceImpl implements ActiveMQService {
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQServiceImpl.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public void sendMessage(Destination destination, String msg) {
        jmsMessagingTemplate.convertAndSend(destination, msg);
    }
}
