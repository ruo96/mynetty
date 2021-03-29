package com.wrh.controller;

import com.wrh.annotate.annotation.PassportTotal;
import com.wrh.controller.service.ActiveMQService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import java.time.LocalDateTime;

/**
 * @author wuruohong
 * @Classname HelloController
 * @Description TODO
 * @Date 2020/2/27 11:32
 */
@Slf4j
@RestController
@PassportTotal
@RequestMapping("activemq/")
public class ActivemqController {

    private static final Logger logger = LoggerFactory.getLogger(ActivemqController.class);

    @Autowired
    private ActiveMQService activeMQService;

    @Autowired
    private Destination queue;

    @RequestMapping(value = "send/queue")
    public String activemq() {
        logger.info(">>> send/queue time: {}", LocalDateTime.now());
        activeMQService.sendMessage(queue,"Queue: hello activemq!");
        return "success";
    }


}
