package com.wrh.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:13 2019/12/6 0006
 * @Modified By:
 */
@Slf4j
@RestController
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/topic/{topic}")
    public void createTopic(@PathVariable("topic") String topic){

        log.info("kafka createTopic start");
        kafkaTemplate.setDefaultTopic(topic);
        log.info("kafka createTopic end");
    }


    @GetMapping("/send/{topic}/{input}")
    public void sendFoo(@PathVariable("topic") String topic,
                        @PathVariable("input") String input){

        log.info("kafka sendMessage start");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, input);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("kafka sendMessage error, ex = {}, topic = {}, data = {}", ex, topic, input);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("kafka sendMessage success topic = {}, data = {}",topic, input);
            }
        });
        log.info("kafka sendMessage end");
    }
}
