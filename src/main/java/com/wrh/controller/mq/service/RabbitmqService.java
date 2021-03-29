package com.wrh.controller.mq.service;

/**
 * @author wuruohong
 * @Classname RabbitmqService
 * @Description TODO
 * @Date 2021/3/29 16:36
 */
public interface RabbitmqService {
    public void sendDelayedMessage(String queueName, String msg);
}
