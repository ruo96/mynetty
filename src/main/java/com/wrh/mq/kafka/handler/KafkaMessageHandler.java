package com.wrh.mq.kafka.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

/**
 * @author wuruohong
 * @Classname KafkaMessageHandler
 * @Description TODO
 * @Date 2020/3/5 14:10
 */
public class KafkaMessageHandler implements MessageListener<String, String> {

    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        String message = record.value();
        String topic = record.topic();
    }
}
