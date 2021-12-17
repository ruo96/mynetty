package com.wrh.listener.fourmethods;

import org.springframework.context.ApplicationListener;

/**
 * @author wuruohong
 * @Classname MyListener1
 * @Description TODO
 * @Date 2021/12/17 10:59
 */

public class MyListener3 implements ApplicationListener<MyEvent> {
    /**
     * 然后在application.properties中配置监听
     * context.listener.classes=com.listener.MyListener3
     * @param event
     */
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("MyListener3:" + event.toString());
    }
}
