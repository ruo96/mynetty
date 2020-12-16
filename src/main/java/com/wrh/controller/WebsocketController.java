package com.wrh.controller;

import com.wrh.domain.websocket.WebsocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author wuruohong
 * @Classname WebsocketController
 * @Description TODO
 * @Date 2020/12/16 18:40
 */
@Slf4j
@Controller
public class WebsocketController {
    @MessageMapping("/hellowebsocket")
    @SendTo("/topic/greetings")
    public WebsocketMessage greeting(WebsocketMessage message) throws Exception {
        log.info(">>> hellowebsocket param: {}", message.toString());
        return message;
    }
}
