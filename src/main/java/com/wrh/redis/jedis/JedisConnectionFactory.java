package com.wrh.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/11/4 10:31
 */
public class JedisConnectionFactory {

    /**
     *   redis:
     *     host: 172.20.8.110
     *     port: 31766
     *     connect-timeout: 10000ms
     *     database: 0
     *     jedis:
     *       pool:
     *         max-active: 20
     *         max-idle: 10
     *         max-wait: -1
     *         min-idle: 0
     */

    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(8);
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMinIdle(0);
        jedisPoolConfig.setMaxWaitMillis(1000);  // 最多等待1000ms

        jedisPool = new JedisPool(jedisPoolConfig, "172.20.8.110", 31766,1000);
    }

    public static Jedis getJedisPool() {
        return jedisPool.getResource();
    }

    public static void main(String[] args) {
//        JedisConnectionFactory factory = new JedisConnectionFactory();
        Jedis jedisPool = JedisConnectionFactory.getJedisPool();
        jedisPool.set("w1","r1");

        String w1 = jedisPool.get("w1");
        System.out.println("w1 = " + w1);

    }


}
