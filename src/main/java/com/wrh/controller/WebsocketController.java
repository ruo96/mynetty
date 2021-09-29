package com.wrh.controller;

import com.wrh.domain.websocket.WebsocketMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class WebsocketController implements ApplicationContextAware {
    @MessageMapping("/hellowebsocket")
    @SendTo("/topic/greetings")
    public WebsocketMessage greeting(WebsocketMessage message) throws Exception {
        log.info(">>> hellowebsocket param: {}", message.toString());
        return message;
    }

    @SneakyThrows
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("[applicationcontext]>>> {}" , applicationContext);
        log.info("[applicationcontext]>>>getDisplayName {}" , applicationContext.getDisplayName());
        log.info("[applicationcontext]>>>getId {}" , applicationContext.getId());
        log.info("[applicationcontext]>>>getParent {}" , applicationContext.getParent());
        log.info("[applicationcontext]>>>getApplicationName {}" , applicationContext.getApplicationName());
        log.info("[applicationcontext]>>>getStartupDate {}" , applicationContext.getStartupDate());
        log.info("[applicationcontext]>>>getClassLoader {}" , applicationContext.getClassLoader());
        log.info("[applicationcontext]>>>getResources {}" , applicationContext.getResources("data.txt").length);
    }
}
