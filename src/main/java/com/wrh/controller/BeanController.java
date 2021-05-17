package com.wrh.controller;

import com.wrh.bean.NormalBean;
import com.wrh.controller.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author wuruohong
 * @Classname FileUploadController
 * @Description TODO
 * @Date 2020/12/8 12:02
 */
@Slf4j
@RestController
public class BeanController {

    @Autowired
    NormalBean normalBean;


    @RequestMapping("/bean")
    public String setBean(HttpServletRequest req){
        log.info(">>> setBean ：{}", LocalDateTime.now());

        return "ok";

    }
}
