package com.wrh.controller;

import com.wrh.annotate.annotation.PassportTotal;
import com.wrh.controller.service.ActiveMQService;
import com.wrh.controller.service.HelloService;
import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
