package com.wrh.designMode.observerGuava;

/**
 * @author wuruohong
 * @Classname EventBusDemoTest
 * @Description TODO
 * @Date 2021/11/11 15:10
 */
public class EventBusDemoTest {

    public static void main(String[] args) {

        EventListener eventListener = new EventListener();
        EventBusCenter.register(eventListener);
        EventBusCenter.post(new NotifyEvent("13372817283", "123@qq.com", "666"));
    }
}
