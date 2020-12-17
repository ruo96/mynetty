package com.wrh.controller;

import com.wrh.controller.domain.User;
import com.wrh.mail.MailService;
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
    MailService mailService;


    @RequestMapping("/mail")
    public String property(HttpServletRequest request, HttpServletResponse response){

        mailService.sendSimpleMail("1303423055@qq.com",
                "314825560@qq.com",
                "ww-1096@163.com",
                "测试邮件主题名称",
                "这是一封测试用邮件");


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
