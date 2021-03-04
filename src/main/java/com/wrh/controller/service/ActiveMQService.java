package com.wrh.controller.service;

import javax.jms.Destination;

/**
 * @author wuruohong
 * @Classname HelloService
 * @Description TODO
 * @Date 2020/7/10 14:46
 */
public interface ActiveMQService {

    public void sendMessage(Destination destination, String msg);
}
