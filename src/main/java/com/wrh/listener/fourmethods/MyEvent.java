package com.wrh.listener.fourmethods;

import org.springframework.context.ApplicationEvent;

/**
 * @author wuruohong
 * @Classname MyEvent
 * @Description TODO
 * @Date 2021/12/17 11:00
 */
public class MyEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
    }
}
