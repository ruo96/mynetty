package com.wrh.mail;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:05 2019/11/7 0007
 * @Modified By:
 */
@Slf4j
public class MailTest {

    @Test
    public void test(){
        boolean isSend = EmailUtils.sendEmail("这是一封测试邮件", new String[]{"314822556@qq.com"}, null, "<h3><a href='http://www.baidu.com'>百度一下，你就知道</a></h3>", null);
        log.info("发送： ", isSend);
    }

    @Test
    public void Test24() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("wrh_1096@163.com");
        msg.setBcc();
        msg.setTo("ww-1096@163.com");
        msg.setSubject("test");
        msg.setText("fenxiang");

        JavaMailSender javaMailSender = new JavaMailSenderImpl();
        javaMailSender.send(msg);
        return;

    }

}
