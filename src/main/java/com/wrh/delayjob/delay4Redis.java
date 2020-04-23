package com.wrh.delayjob;

import org.springframework.data.redis.connection.jedis.JedisUtils;
import redis.clients.jedis.Jedis;

/**
 * @author wuruohong
 * @Classname delay4Redis
 * @Description 使用 Redis 实现延迟任务的方法大体可分为两类：通过 zset 数据判断的方式，和通过键空间通知的方式。
 * ① 通过数据判断的方式
 *
 * 我们借助 zset 数据类型，把延迟任务存储在此数据集合中，然后在开启一个无线循环查询当前时间的所有任务进行消费，实现代码如下（需要借助 Jedis 框架）：
 *
 *
 * @Date 2020/4/16 19:25
 */
public class delay4Redis {

    private static final String key = "myDelayQueue";

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
    }
}
