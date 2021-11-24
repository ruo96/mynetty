package com.wrh.controller.config;

import com.wrh.encode.peizhijiami.ConfigJiamiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuruohong
 * @Classname ConfigPeizhiController
 * @Description TODO
 * @Date 2021/9/8 16:30
 */
@Slf4j
@RestController
@RequestMapping("config/")
public class ConfigPeizhiController {

    @Autowired
    private ConfigJiamiService configJiamiService;

    //@Value("${data.encrypt}")
    private String jiemi;

    @RequestMapping("jiami/{data}")
    public String jiami(HttpServletRequest request, HttpServletResponse response, @PathVariable String data){

        log.info(">>> 需要加密的数据: {}", data);
        /** 4YOz0+Cu4hUBo6qBY4YEsg== */
        return configJiamiService.jiami(data);
    }

    @RequestMapping("jiemi")
    public String jiemi(HttpServletRequest request, HttpServletResponse response){

        log.info(">>> 需要解密的数据: {}", jiemi);
        /** 4YOz0+Cu4hUBo6qBY4YEsg== */
//        String data = configJiamiService.jiemi(jiemi);
        log.info(">>> 解密结果: {}", jiemi);
        return jiemi;
    }
}
