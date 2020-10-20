package com.wrh.websocket;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author wuruohong
 * @Classname AutoConfiguration
 * @Description TODO
 * @Date 2020/10/13 11:18
 */
public class AutoConfiguration implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(),"wsdemo");
    }

    public WebSocketHandler myHandler(){
        return new MyWebSocketHandler();
    }
}
