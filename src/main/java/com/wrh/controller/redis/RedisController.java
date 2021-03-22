package com.wrh.controller.redis;

import com.wrh.utils.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuruohong
 * @Classname RedisController
 * @Description TODO
 * @Date 2021/3/22 11:47
 */
@Slf4j
@RestController
@RequestMapping("redis/")
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("set")
    public String setRedis(String value) {
        log.info(">>> set redis begin");
        redisUtils.set("w1","value1");
        return "ok";
    }
}
