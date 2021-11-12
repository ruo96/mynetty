package com.wrh.designMode.observerGuava;

import lombok.Data;

/**
 * @author wuruohong
 * @Classname NotifyEvent
 * @Description 通知事件类
 * @Date 2021/11/11 15:10
 */
@Data
public class NotifyEvent {
    private String mobileNo;

    private String emailNo;

    private String imNo;

    public NotifyEvent(String mobileNo, String emailNo, String imNo) {
        this.mobileNo = mobileNo;
        this.emailNo = emailNo;
        this.imNo = imNo;
    }
}
