package com.wrh.controller;

import com.google.common.base.Strings;
import com.wrh.controller.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.support.collections.RedisProperties;
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
public class Redis2Controller {

    @Autowired
    TestMapper testMapper;


    @RequestMapping("/redis")
    public String setnx(HttpServletRequest req){
        log.info(">>> setnx ：{}", LocalDateTime.now());


        GenericObjectPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(1);
        poolConfig.setMinIdle(1);
        poolConfig.setTestOnBorrow(false);
        /**
         * 连接池耗尽时候是否阻塞 默认是
         * false 表示直接抛错
         */
        poolConfig.setBlockWhenExhausted(false);
        HostAndPort hostAndPort = HostAndPort.parseString("172.16.38.197:6379");

        JedisPool jedisPool = new JedisPool(poolConfig, hostAndPort.getHost(), hostAndPort.getPort(), 60000, "bili@2233");
        try (Jedis jedis = jedisPool.getResource()) {
            String result = jedis.set("lock_key_dis", "123","NX","PX",60000);
            log.info(">>> dis redis lock is : {}", result);
            if ("OK".equals(result)) {
                log.info(">>> 分布式锁上了");
            } else {
                log.info(">>> 分布式锁 没锁上");
            }
            return result;
        }

    }
}
