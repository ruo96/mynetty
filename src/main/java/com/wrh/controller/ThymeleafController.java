package com.wrh.controller;

import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wuruohong
 * @Classname ThymeleafController
 * @Description TODO
 * @Date 2021/4/6 11:45
 */
@Slf4j
@Controller
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
}
