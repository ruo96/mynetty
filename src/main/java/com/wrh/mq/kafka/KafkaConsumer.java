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
//@Service
public class KafkaConsumer {



    /**
     * kafka操作命令
     * .\kafka-consumer-groups.bat --new-consumer --bootstrap-server 127.0.0.1:9092 --list       .\kafka-consumer-groups.bat --zookeeper 127.0.0.1:2181 --list  这个地方测试是有问题的
     * .\kafka-topics.bat --zookeeper 127.0.0.1:2181 --topic hellonetty4 --describe    查看topic的详情
     * .\kafka-server-start.bat D:\tools\mq\kafka\kafka_2.12-2.4.0\config\server.properties
     * .\kafka-topics.bat --create --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 1 --topic test20220812     创建topic
     * .\kafka-console-producer.bat --broker-list 127.0.0.1:9092 --topic  data-center-mobile-log-test  开启生产者
     * .\kafka-console-consumer.bat --zookeeper localhost:2181 --topic test   开启消费者
     * .\kafka-consumer-groups.sh --bootstrap-server XXX:9092 --group your-group-id --describe  查看组消费问题
     * bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic abc
     * 如果有3个partition，那么就会ABA-ABA-ABA 这样收发数据，如果2个partition，那么AB就会轮流获取，如果只有一个partition，那么就只有A或者只有B能够接收数据
     * -- 查看group消费情况
     * bin/kafka-consumer-groups.sh --bootstrap-server XXX:9092 --group your-group-id --describe
     * @param record
     */
//    @KafkaListener(groupId = "group1",topics = "mynetty3-3p")
    public void processMessage(ConsumerRecord<Integer, String> record) {
        log.info("===> A kafka key: {} topic : {} 接收信息： {}",record.key(), record.topic(), record.value());
    }

//    @KafkaListener(groupId = "group1",topics = "mynetty3-3p")
    public void processMessage1(ConsumerRecord<Integer, String> record) {
        log.info("===> B kafka key: {} topic : {} 接收信息： {}",record.key(), record.topic(), record.value());
    }

//    @KafkaListener(groupId = "group2",topics = "mynetty2")
    public void processMessage2(ConsumerRecord<Integer, String> record) {
        log.info("===> C kafka topic : {} 接收信息： {}",record.topic(), record.value());
    }

//    @KafkaListener(groupId = "group3",topics = "hellonetty4")
    public void processMessage4(ConsumerRecord<Integer, String> record) {
        log.info("===> D kafka topic : {} 接收信息： {}",record.topic(), record.value());
    }
}
