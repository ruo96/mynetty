package com.wrh.controller;

import com.wrh.annotate.annotation.PassportTotal;
import com.wrh.controller.service.HelloService;
import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//@RestController
@PassportTotal
@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/hi")
    public String hi(HttpServletResponse response) throws IOException {
        log.info(">>> hi time: {}", LocalDateTime.now());
        log.info(">>> hi modify 3  time: {}", LocalDateTime.now());
        return "internationalize";
    }

    @RequestMapping(value = "/internationalize")
    public String internationalize(HttpServletResponse response) throws IOException {
        log.info(">>> internationalize  time: {}", LocalDateTime.now());
        return "internationalize";
    }

    /**
     * 动态改变返回国际化信息
     * @param request
     * @param lang
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/changeSessionLanauage")
    public String changeSessionLanauage(HttpServletRequest request,String lang) throws IOException {
        System.out.println(lang);

        if("zh".equals(lang)){

            //代码中即可通过以下方法进行语言设置

            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,new Locale("zh","CN"));

        }else if("en".equals(lang)){

            //代码中即可通过以下方法进行语言设置

            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,new Locale("en","US"));

        }

        return "redirect:/internationalize";
    }

    @RequestMapping(value = "/himodel")
    public String hiModel(Model model) throws IOException {
        log.info(">>> hi model  time: {}", LocalDateTime.now());
        Student s = new Student();
        s.setName("w1");
        s.setId(1);
        s.setGrade(100);
        s.setMoney(200L);
        s.setTitle("title");
        s.setFlag(true);

        model.addAttribute("student", s);
        return "modelshow";

    }

    /** 目前不生效*/
    @RequestMapping(value = "/hiview")
    public ModelAndView hiModelView() throws IOException {
        log.info(">>> hi modelview  time: {}", LocalDateTime.now());
        Student s = new Student();
        s.setName("w1");
        s.setId(1);
        s.setGrade(100);
        s.setMoney(200L);
        s.setTitle("title");
        s.setFlag(true);

        log.info(">>> redirect3  time: {}", LocalDateTime.now());
        ModelAndView model = new ModelAndView("redirect:/modelshow.html");
        /** 这个会自动加入请求后面的参数中*/
        model.addObject("student",s);
        return model;
    }

    @GetMapping(value = "/hello")
    public String hello(){
        return "healthy";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "healthy";
    }

    @GetMapping(value = "/rate")
    public String rate(){
        while (true){

            System.out.println("outer req once: time ：" + LocalDateTime.now().toString());
            helloService.hello();
        }
    }

    @GetMapping(value = "/rate/hand")
    public String handRate(){
            System.out.println("outer req once: time ：" + LocalDateTime.now().toString());
           return helloService.hello();
    }

    /**
     * 重定向
     * @return
     */
    @GetMapping(value = "/redirect")
    public ModelAndView redirect(HttpServletResponse response) throws IOException {
        log.info(">>> redircet  time: {}", LocalDateTime.now());
        response.sendRedirect("/index");
        return null;
    }

    @GetMapping(value = "/redirect1")
    public String redirect1(HttpServletResponse response) throws IOException {
        log.info(">>> redirect1  time: {}", LocalDateTime.now());
        return "redirect:/index";
    }

    @RequestMapping(value = "/redirect2")
    public ModelAndView redirect2(Model model, RedirectAttributes attr, HttpServletResponse response) throws IOException {
        log.info(">>> redirect2  time: {}", LocalDateTime.now());
        attr.addAttribute("test","51gjie");
        attr.addFlashAttribute("u2","51gjie");
        response.sendRedirect("/user/users");
        return null;
    }

    @RequestMapping(value = "/redirect3")
    public ModelAndView redirect3() throws IOException {
        log.info(">>> redirect3  time: {}", LocalDateTime.now());
        ModelAndView model = new ModelAndView("redirect:/example");
        /** 这个会自动加入请求后面的参数中*/
        model.addObject("username","wrh");
        return model;
    }

    @RequestMapping(value = "/example")
    public String redirect4(HttpServletRequest request) throws IOException {
        log.info(">>> redirect4  time: {}", LocalDateTime.now());
        String name = request.getParameter("username");
        return "welcome to example  " + name + "!";
    }

    @RequestMapping(value = "/example123")
    public String redirect5(HttpServletRequest request) throws IOException {
        log.info(">>> redirect4  time: {}", LocalDateTime.now());
        String uri = request.getRequestURI();
        String url = request.getRequestURL().toString();
        System.out.println("uri");
        log.info(">>> uri: {}", uri);
        System.out.println("url");
        log.info(">>> url: {}", url);
        return "ok";
    }

    @Test
    public void Test113() {
        String ip2 = "172.18.9.1,172.18.90.90";
        if(StringUtils.isNotBlank(ip2)){
            if(ip2.contains(",")){
                String[] s = ip2.split(",");
                ip2 = ip2.split(",")[s.length-1];
            }
        }
        System.out.println(ip2);

    }

    public final static List<String> ipList = new ArrayList<String>(Arrays.asList("180.168.177.4","27.115.6.196","120.253.194.41","218.189.16.248"
            ,"103.108.180.9","202.153.91.79","103.108.180.36"));

    public final static List<String> ipListOffice = new ArrayList<String>(Arrays.asList("172.16.","172.17.","172.18.","172.19."
            ,"172.20.","172.21.","172.22.","172.23.","172.24.","172.25.","172.26.","172.27.","172.28.","172.29.","172.30.","172.31."));

    public final static List<String> mobileRealIp = new ArrayList<>(Arrays.asList("180.168.177.4","27.115.6.196","120.253.194.41"));

    @Test
    public void Test128() {
        String ip = "172.18.11.1";

        log.info("[checkVpn]>>> out checkVpn  ip:{}",  ip);
        if(ipList.contains(ip) || ip.startsWith("10.")
                || mobileRealIp.stream().filter(e->ip.startsWith(e)).findFirst().isPresent()
                || ipListOffice.stream().filter(e->ip.startsWith(e)).findFirst().isPresent()){
            log.info("[checkVpn]>>> in checkVpn  ip:{}",  ip);

        }


    }
}
