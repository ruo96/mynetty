package com.wrh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wuruohong
 * @Classname LoginController
 * @Description TODO
 * @Date 2020/2/27 18:46
 */
@Slf4j
@RestController
public class PropertyController {

    @Value("#{'${test.list:}'.empty ? null : '${test.list:}'.split(',')}")
    private List<String> testList;


    @RequestMapping("/property")
    public String property(HttpServletRequest request, HttpServletResponse response){

        log.info("[property]>>> testList: {}", testList);


        return "ok";
    }



}
