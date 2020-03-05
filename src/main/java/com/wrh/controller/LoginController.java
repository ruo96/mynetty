package com.wrh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class LoginController {

    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        //跳转至登录界面
        log.info(">>> 调用登录接口，进行跳转");
        ModelAndView modelAndView = new ModelAndView("login.html");
        return modelAndView;
    }

    @PostMapping("/loginIn")
    public Map<String,Object> loginIn(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        String userName = request.getParameter("userName");
        log.info("===> 请求登录的用户： {}",userName);
        if("itmsbx".equals(userName)){
            request.getSession().setAttribute("userSession", new Object());
            map.put("result" , "1");
        }else{
            map.put("result" , "-1");
        }
        return map;
    }

    @RequestMapping("/manager")
    public ModelAndView manager(HttpServletRequest request, HttpServletResponse response){
        //跳转至管理后台
        ModelAndView modelAndView = new ModelAndView("manager");
        return modelAndView;
    }
}
