package com.wrh.controller;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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

    /*@Autowired
    TestMapper testMapper;*/

    @Autowired
    private RedisTemplate redisTemplate;


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
                log.info(">>> 分布式锁 没锁上123");
            }
            return result;
        }

    }

    private final String REDIS_SCRIPT = buildLuaScript();

    private String buildLuaScript() {
        StringBuilder lua = new StringBuilder();
        lua.append("local c")
                .append("\nc = redis.call('get', KEYS[1])")
                // 调用不超过最大值，则直接返回
                .append("\nif c and tonumber(c) > tonumber(ARGV[1]) then")
                .append("\nreturn c;")
                .append("\nend")
                // 执行计算器自加
                .append("\nc = redis.call('incr', KEYS[1])")
                .append("\nif tonumber(c) == 1 then")
                // 从第一次调用开始限流，设置对应键值的过期
                .append("\nredis.call('expire', KEYS[1], ARGV[2])")
                .append("\nend")
                .append("\nreturn c;");
        return lua.toString();
    }

    private static final String UNKNOWN = "unknown";


    public String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @RequestMapping("/redis/lua")
    public String redisLua(HttpServletRequest req){
        RedisScript<Number> redisScript = new DefaultRedisScript<Number>(REDIS_SCRIPT, Number.class);

        ImmutableList<String> keys = ImmutableList.of("123","456");
        int limitPeriod = 1;
        int limitCount = 1;
        Number count = (Number) redisTemplate.execute(redisScript, keys,  limitCount, limitPeriod);

        return "";
    }
}
