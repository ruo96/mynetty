package com.wrh.controller;

import com.wrh.controller.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.utils.Java;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author wuruohong
 * @Classname LoginController
 * @Description TODO
 * @Date 2020/2/27 18:46
 */
@Slf4j
@RestController
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;


    @RequestMapping("/mail")
    public String property(HttpServletRequest request, HttpServletResponse response){

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("wrh_1096@163.com");
        msg.setBcc();
        msg.setTo("ww-1096@163.com");
        msg.setSubject("test");
        msg.setText("fenxiang");

        javaMailSender.send(msg);


        return "ok";
    }

    /**
     * 测试统一日期格式返回
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/timeformate")
    public User timeFormatter(HttpServletRequest request, HttpServletResponse response){

        User user = new User("id1","wrh", LocalDateTime.now());
        return user;
    }





}
