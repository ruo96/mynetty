package com.wrh.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author wuruohong
 * @Classname MyWebSockerHandler
 * @Description TODO
 * @Date 2020/10/13 11:20
 */
public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        /** 获取接收的数据*/
        String payload = message.getPayload();

        /** 向客户端发送数据*/
        session.sendMessage(new TextMessage("response:" + payload));
    }
}
