package com.wrh.controller.auth;

import com.wrh.auth.AuthParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuruohong
 * @Classname ListenerController
 * @Description TODO
 * @Date 2021/12/17 11:03
 */
@Slf4j
@RestController
@RequestMapping("/auth/")
public class AuthController {

    @RequestMapping("go")
    public String go(HttpServletRequest request, AuthParam authParam, HttpServletResponse response){
        System.out.println("request.getParameter(\"auth\") = " + request.getParameter("auth"));
        System.out.println("request.getParameter(\"name\") = " + request.getParameter("name"));
        System.out.println("auth go");
        log.info(">>> authParam: {}", authParam);

        return "ok";
    }
}
