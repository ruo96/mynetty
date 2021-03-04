package com.wrh.controller.mq;

import com.wrh.config.ActiveMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author wuruohong
 * @Classname QueueConsumer
 * @Description TODO
 * @Date 2021/3/3 16:56
 */
//@Service
public class QueueConsumer {
    private static final Logger logger = LoggerFactory.getLogger(QueueConsumer.class);

//    @JmsListener(destination = ActiveMqConfig.QUEUE_NAME)
    public void receiveQueueMsg(String msg) {
        logger.info("收到的消息为：{}",msg);
    }
}
