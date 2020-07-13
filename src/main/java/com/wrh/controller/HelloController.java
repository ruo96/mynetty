package com.wrh.controller;

import com.wrh.annotate.annotation.PassportTotal;
import com.wrh.controller.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
