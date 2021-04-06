package com.wrh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @author wuruohong
 * @Classname CookieController
 * @Description TODO
 * @Date 2020/11/30 10:52
 */
@Slf4j
@Controller
@SessionAttributes("name")
@RequestMapping("/attribute/")
public class AttributeController {

    @GetMapping("/01")
    public String attr01(Model model) {
        log.info(">>> attribute 01");
        model.addAttribute("name","w1");
        return "forward:/attribute/03";
    }


    @GetMapping("/02")
    public String attr02(Model model) {
        log.info(">>> attribute 02");
        model.addAttribute("name","w2");
        return "redirect:/attribute/03";
    }

    @GetMapping("/03")
    public String attr03() {
        log.info(">>> attribute 03");
        return "attribute01";
    }

    @GetMapping("/04")
    @ResponseBody
    public void hello4(@SessionAttribute("name") String name) {
        log.info(">>> attribute 04");
        System.out.println("name = " + name);
    }

    @GetMapping("/05")
    @ResponseBody
    public void hello5(SessionStatus sessionStatus) {
        log.info(">>> attribute 05");
        sessionStatus.setComplete();
    }
}
