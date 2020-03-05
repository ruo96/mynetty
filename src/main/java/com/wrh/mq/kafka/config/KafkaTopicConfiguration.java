package com.wrh.mq.kafka.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuruohong
 * @Classname KafkaTopicConfiguration
 * @Description TODO
 * @Date 2020/3/5 15:06
 */
@Configuration
@EnableConfigurationProperties(KafkaTopicProperties.class)
public class KafkaTopicConfiguration {
    private final KafkaTopicProperties properties;

    public KafkaTopicConfiguration(KafkaTopicProperties kafkaTopicProperties) {
        this.properties = kafkaTopicProperties;
    }

    @Bean
    public String[] kafkaTopicName() {
        return properties.getTopicName();
    }

    @Bean
    public String topicGroupId() {
        return properties.getGroupId();
    }
}
