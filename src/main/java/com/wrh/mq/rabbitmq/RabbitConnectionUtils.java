package com.wrh.mq.rabbitmq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author wuruohong
 * @Classname RabbitConnectionUtils
 * @Description TODO
 * @Date 2020/11/27 11:13
 */
@Slf4j
public class RabbitConnectionUtils {
    /**
     * 连接器
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/vhost_mmr");
        factory.setUsername("wrh");
        factory.setPassword("rabbit123");
        Connection connection = factory.newConnection();
        return connection;
    }

    public static void send(String msg, String queue) throws IOException, TimeoutException {
        // 获取一个连接
        Connection connection = getConnection();
        // 从连接获取一个通道
        Channel channel = connection.createChannel();
        // 创建队列声明
        // 队列声明  队列名，是否持久化，是否独占模式，无消息后是否自动删除，消息携带参数
//        exchange type durable autoDelete arguments
        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(queue, true, false, false, null);

        // exchange，队列，参数，消息字节体
        channel.basicPublish("", queue, null, msg.getBytes());
        // 每个消费者发送确认消息之前，消息队列不发送下一个消息到消费者，一次只发送一个消息
        // 从而限制一次性发送给消费者到消息不得超过1个。
        channel.basicQos(1);

        log.info(">>> send msg: {}", msg);

        channel.close();

        connection.close();

    }

    public static void recv( String queue) throws IOException, TimeoutException {
        // 获取一个连接
        Connection connection = getConnection();
        // 从连接获取一个通道
        Channel channel = connection.createChannel();
        // 创建队列声明
        channel.queueDeclare(queue, true, false, false, null);

        // 定义消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override  // 事件模型，消息来了会触发该函数
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body, "utf-8");
                log.info(">>> consume msg: {}", s);
            }
        };
        // 监听队列
        channel.basicConsume(queue,true,defaultConsumer);

    }

    public static void sendByExchange(String exchangeName, String msg) throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(exchangeName,"fanout");// 分发= fanout

        channel.basicPublish(exchangeName,"",null,msg.getBytes());
        log.info(">>> sendByExchange msg: {}", msg);

        channel.close();
        connection.close();
    }

    public static void recvByExchange( String queue, String exchange) throws IOException, TimeoutException {
        // 获取一个连接
        Connection connection = getConnection();
        // 从连接获取一个通道
        final Channel channel = connection.createChannel();
        // 创建队列声明
        channel.queueDeclare(queue, false, false, false, null);
        // 绑定队列到交换机转发器
        channel.queueBind(queue, exchange,"");
        // 保证一次只分发一个
        channel.basicQos(1);

        // 定义消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override  // 事件模型，消息来了会触发该函数
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body, "utf-8");
                log.info(">>> recvByExchange msg: {}", s);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.info(">>> recvByExchange InterruptedException: {}", e);
                } finally {
                    log.info(">>> recvByExchange msg done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        // 监听队列
        boolean autoAck = false;
        channel.basicConsume(queue,autoAck,defaultConsumer);

    }


}
