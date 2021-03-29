package com.wrh.controller.mq;

import com.wrh.controller.mq.service.RabbitmqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wuruohong
 * @Classname RabbitMqDelayController
 * @Description TODO
 * @Date 2021/3/29 16:33
 */
@Slf4j
@RestController
@RequestMapping("/rabbitmq/")
public class RabbitMqDelayController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @RequestMapping("delay")
    public String sendDelayedMessage(HttpServletRequest request) {
        String msg = request.getParameter("msg");
        rabbitmqService.sendDelayedMessage("test_queue_1", msg);
        return "ok";
    }
}
