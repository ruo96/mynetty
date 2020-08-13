package com.wrh.spring;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;

public class TestEventListener implements ApplicationListener<WebServerInitializedEvent> {
    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        System.out.println(">>>>>>namespace:   "+webServerInitializedEvent.getApplicationContext().getServerNamespace());
        System.out.println("事件监听器启动");
    }
}
