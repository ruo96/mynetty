package com.wrh.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:18 2019/12/6 0006
 * @Modified By:
 */
@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(id = "group123",topics = "mynetty123")
    public void processMessage(ConsumerRecord<Integer, String> record) {
      log.info("===> kafka 接收信息： {}",record.value());
    }
}
