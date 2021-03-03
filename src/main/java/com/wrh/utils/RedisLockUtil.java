package com.wrh.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * @author wuruohong
 * @Classname RedisLockUtil
 * @Description 分布式锁的实现
 * @Date 2021/2/8 18:16
 */
public class RedisLockUtil {

    private String LOCK_KEY = "redis_lock";

    // key的持有时间，5ms
    private long EXPIRE_TIME = 5;

    // 等待超时时间，1s
    private long TIME_OUT = 1000;

    // redis命令参数，相当于nx和px的命令合集
//    private SetParams params = SetParams.setParams().nx().px(EXPIRE_TIME);

    // redis连接池，连的是本地的redis客户端
    JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);

    /**
     * 加锁
     *
     * @param id
     *            线程的id，或者其他可识别当前线程且不重复的字段
     * @return
     */
    public boolean lock(String id) {
        Long start = System.currentTimeMillis();
        Jedis jedis = jedisPool.getResource();
        try {
            for (;;) {
                // SET命令返回OK ，则证明获取锁成功
//                String lock = jedis.set(LOCK_KEY, id, params);
                String lock = jedis.set(LOCK_KEY, id, "NX","EX",EXPIRE_TIME);
                if ("OK".equals(lock)) {
                    return true;
                }
                // 否则循环等待，在TIME_OUT时间内仍未获取到锁，则获取失败
                long l = System.currentTimeMillis() - start;
                if (l >= TIME_OUT) {
                    return false;
                }
                try {
                    // 休眠一会，不然反复执行循环会一直失败
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            jedis.close();
        }
    }

    /**
     * 解锁
     *
     * @param id
     *            线程的id，或者其他可识别当前线程且不重复的字段
     * @return
     */
    public boolean unlock(String id ) {
        Jedis jedis = jedisPool.getResource();
        // 删除key的lua脚本
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then" + "   return redis.call('del',KEYS[1]) " + "else"
                + "   return 0 " + "end";
        try {
            String result =
                    jedis.eval(script, Collections.singletonList(LOCK_KEY), Collections.singletonList(id)).toString();
            return "1".equals(result);
        } finally {
            jedis.close();
        }
    }
}
