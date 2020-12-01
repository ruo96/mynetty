package com.wrh.mq.rabbitmq;

import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author wuruohong
 * @Classname TestRabbitMQ
 * @Description TODO
 * @Date 2020/11/27 11:12
 */
@Slf4j
public class TestRabbitMQ {
//    public static final String RABBIT_QUEUE = "test";
    public static final String RABBIT_QUEUE = "queue_test";
    public static final String EXCHANGE_NAME = "exchange_test";
    public static final String RABBIT_QUEUE_1 = "queue_test_1";
    @Test
    public void Test14() throws IOException, TimeoutException {
        Connection conn = RabbitConnectionUtils.getConnection();
        log.info(">>> conn: {}", conn);
        RabbitConnectionUtils.send("hello everybody", RABBIT_QUEUE);

    }
    @Test
    public void Test27() throws IOException, TimeoutException, InterruptedException {
        RabbitConnectionUtils.recv(RABBIT_QUEUE);
        TimeUnit.SECONDS.sleep(5);

    }

    /**
     * 1 一个生产者多个消费者
     * 2 每一个消费者都有一个自己的队列
     * 3 生产者没有把消息直接发送到队列而是发送到了交换机转化器(exchange)。
     * 4 每一个队列都要绑定到交换机上。
     * 5 生产者发送的消息经过交换机到达队列，从而实现一个消息被多个消费者消费。
     */
    @Test
    public void Test43() throws IOException, TimeoutException {
        RabbitConnectionUtils.sendByExchange(EXCHANGE_NAME, "hello exchange"+ LocalDateTime.now().toString());
        RabbitConnectionUtils.recvByExchange(RABBIT_QUEUE_1 ,EXCHANGE_NAME);
    }

    @Test
    public void Test52() {
        /**  springboot 专用*/

    }
}
