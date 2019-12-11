package com.wrh.xianliu;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:12 2019/12/10 0010
 * @Modified By:
 */
@Slf4j
public class TestXianLiu {
    @Test
    public void test(){
        RateLimiter r = RateLimiter.create(2,3, TimeUnit.SECONDS);

        while (true) {
            /**
             * acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
             * acquire(N)可以获取多个令牌。
             */
            System.out.println("========================");
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
        }
    }
}
