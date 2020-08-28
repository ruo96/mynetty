package com.wrh.controller;

import com.wrh.controller.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuruohong
 * @Classname LoginController
 * @Description TODO
 * @Date 2020/2/27 18:46
 */
@Slf4j
@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @RequestMapping("/api")
    public String api(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("username");
        String userName1 = (String) request.getAttribute("username");
        log.info("===> 请求登录的用户： {}",userName);
        log.info("===> 请求登录的用户 1： {}",userName1);

        return "ok";
    }

    @RequestMapping("/api/exclude")
    public String apiExclude(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("username");
        String userName1 = (String) request.getAttribute("username");
        log.info("===> 请求登录的用户： {}",userName);
        log.info("===> 请求登录的用户 1： {}",userName1);

        return "ok";
    }

    @RequestMapping("/app")
    public String app(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("username");
        String userName1 = (String) request.getAttribute("username");
        log.info("===> 请求登录的用户： {}",userName);
        log.info("===> 请求登录的用户 1： {}",userName1);

        return "ok";
    }

    @RequestMapping("/peizhi")
    public String peizhi(HttpServletRequest request, HttpServletResponse response){
        log.info("===> 获取配置信息 1： {}", LocalDateTime.now().toString());
        return apiService.getParam();
    }


}
