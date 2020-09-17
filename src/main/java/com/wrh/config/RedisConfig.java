package com.wrh.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

/**
 * @Author: wrh
 * @Description:
 * @Date: 下午 2:10 2019/4/11 0011
 */


//@Configuration
//@EnableCaching
public class RedisConfig {
    @Value("${spring.redis2.database}")
    private int remoteDbIndex;

    @Value("${spring.redis2.host}")
    private String remoteHost;

    @Value("${spring.redis2.port}")
    private int remotePort;

    @Value("${spring.redis2.password}")
    private String remotePassword;

    @Value("${spring.redis2.timeout}")
    private int remoteTimeout;



    @Value("${spring.redis.database}")
    private int dbIndex;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;



    @Value("${spring.redis.pool.max-active}")
    private int redisPoolMaxActive;

    @Value("${spring.redis.pool.max-wait}")
    private int redisPoolMaxWait;

    @Value("${spring.redis.pool.max-idle}")
    private int redisPoolMaxIdle;

    @Value("${spring.redis.pool.min-idle}")
    private int redisPoolMinIdle;

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(o.getClass().getName())
                        .append(method.getName());
                for (Object object : objects) {
                    stringBuilder.append(object.toString());
                }
                return stringBuilder.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
//        return new RedisCacheManager(redisTemplate);
        return null;
    }


    @Bean("defaultRedisConnectionFactory")
    public RedisConnectionFactory defaultRedisConnectionFactory() {
        return createFactory(dbIndex, host, port, password, timeout);
    }

    @Bean(name = "redisTemplate")
    @DependsOn("defaultRedisConnectionFactory")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory defaultRedisConnectionFactory) {
         return createRedisTemplate(defaultRedisConnectionFactory);
    }


    @Bean("remoteRedisConnectionFactory")
    public RedisConnectionFactory remoteRedisConnectionFactory() {
       return createFactory(remoteDbIndex, remoteHost, remotePort, remotePassword, remoteTimeout);
    }


    @Bean(name = "remoteRedisTemplate")
    @DependsOn("remoteRedisConnectionFactory")
    public RedisTemplate<Object, Object> remoteRedisTemplate(RedisConnectionFactory remoteRedisConnectionFactory) {
        return createRemoteRedisTemplate(remoteRedisConnectionFactory);
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(defaultRedisConnectionFactory());
        template.afterPropertiesSet();
        return template;
    }


    public RedisTemplate<Object, Object> createRedisTemplate(RedisConnectionFactory defaultRedisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(defaultRedisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //设置键（key）的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置值（value）的序列化方式
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    private RedisTemplate<Object, Object> createRemoteRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置键（key）的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置值（value）的序列化方式
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    private JedisConnectionFactory createFactory(int dbIndex, String host
            ,int port, String password, int timeout){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setDatabase(dbIndex);
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.setTimeout(timeout);
        jedisConnectionFactory.setPoolConfig(setPoolConfig(redisPoolMaxIdle,
                redisPoolMinIdle, redisPoolMaxActive, redisPoolMaxWait, true));
        return jedisConnectionFactory;
    }

    private JedisPoolConfig setPoolConfig(int maxIdle, int minIdle, int maxActive, int maxWait, boolean testOnBorrow) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setTestOnBorrow(testOnBorrow);
        return poolConfig;
    }
}
