package com.wrh.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuruohong
 * @Classname HealthyController
 * @Description 健康检查接口
 * @Date 2020/2/24 13:36
 */
@RestController
public class HealthyController {

    /**
     * 健康检查接口
     * @return
     */
    @GetMapping(value = "/healthy.html")
    public String healthy(){
        return "SUCCESS";
    }
}
