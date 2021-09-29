package com.wrh.spring.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;

/**
 * @author wuruohong
 * @Classname EmailEvent
 * @Description TODO
 * @Date 2021/8/31 11:37
 */
public class EmailEvent extends ApplicationEvent {

    private String address;

    private String text;

    public EmailEvent(Object source, String address, String text) {
        super(source);
        this.address = address;
        this.text = text;
    }

    public EmailEvent(Object source) {
        super(source);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
