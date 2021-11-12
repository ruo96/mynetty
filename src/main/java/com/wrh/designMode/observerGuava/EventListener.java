package com.wrh.designMode.observerGuava;

import com.google.common.eventbus.Subscribe;

/**
 * @author wuruohong
 * @Classname EventListener
 * @Description TODO
 * @Date 2021/11/11 15:09
 */
public class EventListener {

    @Subscribe //加了订阅，这里标记这个方法是事件处理方法
    public void handle(NotifyEvent notifyEvent) {
        System.out.println("发送IM消息" + notifyEvent.getImNo());
        System.out.println("发送短信消息" + notifyEvent.getMobileNo());
        System.out.println("发送Email消息" + notifyEvent.getEmailNo());
    }
}
