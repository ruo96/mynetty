package com.wrh.spring;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class TestRefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println(">>>>>>contextRefreshedEvent applicationName:   "+ contextRefreshedEvent.getApplicationContext().getApplicationName());
        System.out.println(">>>>>>contextRefreshedEvent displayName:   "+ contextRefreshedEvent.getApplicationContext().getDisplayName());
        System.out.println("事件监听器启动");
    }
}
