package com.wrh.controller;

import com.wrh.controller.domain.User;
import com.wrh.mail.MailService;
import com.wrh.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

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

    @RequestMapping("/mail/send")
    public ModelAndView sendMail(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new ModelAndView("sendMail");
        //mv.addObject("from", "wrh");
        return mv;
    }


    @RequestMapping("/mail")
    public String property(HttpServletRequest request, HttpServletResponse response){

        mailService.sendSimpleMail("1303423055@qq.com",
                "314825560@qq.com",
                "ww-1096@163.com",
                "测试邮件主题名称",
                "这是一封测试用邮件");


        return "ok";
    }

    @RequestMapping("/mailwithfile")
    public String mailWithFile(MultipartFile file, HttpServletRequest request){

        String sendto = request.getParameter("sendto");
        String content = request.getParameter("content");
        String subject = request.getParameter("subject");

        try {
            mailService.sendAttachFileMail("1303423055@qq.com",
                    sendto,
                    "ww-1096@163.com",
                    subject,
                    content, FileUtils.MultipartFile2File(file));
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }

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
