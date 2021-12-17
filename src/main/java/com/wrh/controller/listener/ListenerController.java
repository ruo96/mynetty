package com.wrh.controller.listener;

import com.wrh.listener.fourmethods.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuruohong
 * @Classname ListenerController
 * @Description TODO
 * @Date 2021/12/17 11:03
 */
@Slf4j
@RestController
@RequestMapping("/listener/")
public class ListenerController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("publish1")
    public String publishEvent(HttpServletRequest request, HttpServletResponse response){
        String event = request.getParameter("event");
        log.info(">>> publishEvent: {}", event);
        log.info(">>> applicationContext: {}", applicationContext);

        MyEvent e = new MyEvent(event);
        applicationContext.publishEvent(e);

        return "ok";
    }
}
