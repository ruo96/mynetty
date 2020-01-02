package com.wrh.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OnlineSessionListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println(" 监听器 系统启动了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println(" 监听器 系统停止了");
    }
}
