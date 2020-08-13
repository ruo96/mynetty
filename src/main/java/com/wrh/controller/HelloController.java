package com.wrh.controller;

import com.wrh.annotate.annotation.PassportTotal;
import com.wrh.controller.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "hello";
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

}
