package com.wrh.controller;

import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author wuruohong
 * @Classname ThymeleafController
 * @Description TODO
 * @Date 2021/4/6 11:45
 */
@Slf4j
@Controller
@SessionAttributes("name")
@RequestMapping("/thymeleaf/")
public class ThymeleafController {
    @RequestMapping("student")
    public ModelAndView getStudent() {
        log.info(">>> thymeleaf return ");
        Student s = new Student();
        s.setName("w1");
        s.setId(1);
        s.setGrade(2);
        s.setMoney(3L);
        s.setTitle("t1");
        s.setFlag(true);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student",s);
        modelAndView.setViewName("modelshow");
        return modelAndView;
    }

    @RequestMapping("student1")
    public String getStudent1(Model model) {
        log.info(">>> thymeleaf  model return ");
        Student s = new Student();
        s.setName("w1");
        s.setId(1);
        s.setGrade(2);
        s.setMoney(3L);
        s.setTitle("t1");
        s.setFlag(true);
        model.addAttribute("student",s);
        return "modelshow";
    }

    @GetMapping("/01")
    public String hello(Model model) {
        log.info("[ThymeleafController]>>> enter 01");
        model.addAttribute("name", "wrh1");
        return "index1";
    }

    @GetMapping("/02")
    public String hello2(Model model) {
        model.addAttribute("name", "wrh2");
        return "redirect:/index1";
    }

    @GetMapping("/03")
    @ResponseBody
    public void hello3(Model model) {
        //Object name = model.getAttribute("name");
        //System.out.println("name = " + name);
        Map<String, Object> map = model.asMap();
        System.out.println("map = " + map);
    }

    @GetMapping("/04")
    @ResponseBody
    public void hello4(@SessionAttribute("name") String name) {
        System.out.println("name = " + name);
    }

    @GetMapping("/index")
    public String index() {
        return "01";
    }

    @GetMapping("/05")
    @ResponseBody
    public void hello5(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
    }
}
