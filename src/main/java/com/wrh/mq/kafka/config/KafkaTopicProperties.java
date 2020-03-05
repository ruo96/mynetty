package com.wrh.mq.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author wuruohong
 * @Classname KafkaTopicProperties
 * @Description TODO
 * @Date 2020/3/5 15:05
 */
@Component
@ConfigurationProperties("kafka.topic")
public class KafkaTopicProperties implements Serializable {
    private String groupId;
    private String[] topicName;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String[] getTopicName() {
        return topicName;
    }

    public void setTopicName(String[] topicName) {
        this.topicName = topicName;
    }
}
