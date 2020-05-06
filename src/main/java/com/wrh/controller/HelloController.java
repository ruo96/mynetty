package com.wrh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuruohong
 * @Classname HelloController
 * @Description TODO
 * @Date 2020/2/27 11:32
 */
@Slf4j
@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "hello";
    }
}
