package com.wrh.designMode.observerGuava;

import com.google.common.eventbus.EventBus;

/**
 * @author wuruohong
 * @Classname EventBusCenter
 * @Description TODO
 * @Date 2021/11/11 15:08
 */
public class EventBusCenter {
    private static EventBus eventBus = new EventBus();

    private EventBusCenter() {
    }

    public static EventBus getInstance() {
        return eventBus;
    }
    //添加观察者
    public static void register(Object obj) {
        eventBus.register(obj);
    }
    //移除观察者
    public static void unregister(Object obj) {
        eventBus.unregister(obj);
    }
    //把消息推给观察者
    public static void post(Object obj) {
        eventBus.post(obj);
    }
}
