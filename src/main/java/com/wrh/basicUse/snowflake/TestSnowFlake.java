package com.wrh.basicUse.snowflake;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.SneakyThrows;
import org.junit.Test;

import javax.annotation.PostConstruct;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/9/2 15:09
 */
public class TestSnowFlake {

    private long workerId = 0;
    private long datacenterId = 1;
    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

    @PostConstruct
    public void init() {
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test11() throws InterruptedException {
        System.out.println("workerId = " + workerId);
        TestSnowFlake testSnowFlake = new TestSnowFlake();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    System.out.println("1----testSnowFlake.snowFlakeId() = " + testSnowFlake.snowFlakeId());
                    Thread.sleep(1000);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    System.out.println("2----testSnowFlake.snowFlakeId() = " + testSnowFlake.snowFlakeId());
                    Thread.sleep(1000);
                }

            }
        }).start();

        Thread.sleep(10000);
    }

    public synchronized long snowFlakeId() {
        return this.snowflake.nextId();
    }
}
