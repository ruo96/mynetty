package com.wrh.controller;

import com.wrh.controller.service.HystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author wuruohong
 * @Classname HystrixController
 * @Description TODO
 * @Date 2020/8/14 11:19
 */
@Slf4j
@RestController
public class HystrixController {

    @Autowired
    private HystrixService hystrixService;

    @RequestMapping("/hystrix/test")
    public String hystrixTest(HttpServletRequest request, HttpServletResponse response){
        log.info("===> /hystrix/test： {}", LocalDateTime.now().toString());

        return hystrixService.hello();

    }
}
