package com.wrh.domain.websocket;

import lombok.Data;

/**
 * @author wuruohong
 * @Classname WebsocketMessage
 * @Description TODO
 * @Date 2020/12/16 18:43
 */
@Data
public class WebsocketMessage {
    private String name;
    private String content;
}
