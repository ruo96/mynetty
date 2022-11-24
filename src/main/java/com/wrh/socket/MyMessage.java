package com.wrh.socket;

import lombok.Data;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/11/23 17:53
 */
@Data
public class MyMessage {
    /** 消息类型*/
    private String type;

    /** 内容*/
    private String content;

    /** 从哪里来*/
    private String from;

    /** 给谁*/
    private String to;

    /** 频道*/
    private String channel;

}
