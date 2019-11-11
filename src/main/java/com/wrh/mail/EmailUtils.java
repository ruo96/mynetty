package com.wrh.mail;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:15 2019/11/7 0007
 * @Modified By:
 */
@Slf4j
public class EmailUtils {

    @Autowired
    private Environment env;

    private static String auth;
    private static String host;
    private static String protocol;
    private static int port;
    private static String authName;
    private static String password;
    private static boolean isSSL;
    private static String charset ;
    private static String timeout;

    @PostConstruct
    public void initParam () {
        auth = env.getProperty("spring.mail.auth");
        host = env.getProperty("spring.mail.host");
        protocol = env.getProperty("spring.mail.protocol");
        port = env.getProperty("spring.mail.port", Integer.class);
        authName = env.getProperty("spring.mail.username");
        password = env.getProperty("spring.mail.password");
        charset = env.getProperty("spring.mail.default-encoding");
        isSSL = env.getProperty("spring.mail.ssl", Boolean.class);
        timeout = env.getProperty("spring.mail.timeout");
    }


    /**
     * 发送邮件
     * @param subject 主题
     * @param toUsers 收件人
     * @param ccUsers 抄送
     * @param content 邮件内容
     * @param attachfiles 附件列表  List<Map<String, String>> key: name && file
     */
    public static boolean sendEmail(String subject, String[] toUsers, String[] ccUsers, String content, List<Map<String, String>> attachfiles) {
        boolean flag = true;
        try {
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost(host);
            javaMailSender.setUsername(authName);
            javaMailSender.setPassword(password);
            javaMailSender.setDefaultEncoding(charset);
            javaMailSender.setProtocol(protocol);
            javaMailSender.setPort(port);

            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", auth);
            properties.setProperty("mail.smtp.timeout", timeout);
            if(isSSL){
                MailSSLSocketFactory sf = null;
                try {
                    sf = new MailSSLSocketFactory();
                    sf.setTrustAllHosts(true);
                    properties.put("mail.smtp.ssl.enable", "true");
                    properties.put("mail.smtp.ssl.socketFactory", sf);
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
            }
            javaMailSender.setJavaMailProperties(properties);

            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
            messageHelper.setTo(toUsers);
            if (ccUsers != null && ccUsers.length > 0) {
                messageHelper.setCc(ccUsers);
            }
            messageHelper.setFrom(authName);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            if (attachfiles != null && attachfiles.size() > 0) {
                for (Map<String, String> attachfile : attachfiles) {
                    String attachfileName = attachfile.get("name");
                    File file = new File(attachfile.get("file"));
                    messageHelper.addAttachment(attachfileName, file);
                }
            }
            javaMailSender.send(mailMessage);

        } catch (Exception e) {
            log.error("发送邮件失败!", e);
            flag = false;
        }
        return flag;
    }

}
