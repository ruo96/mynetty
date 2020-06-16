package com.wrh.TimeCalculate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:42 2019/9/4 0004
 * @Modified By:
 */
@Slf4j
public class TestTimeCal {
    @Test
    public void test() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        TimeUnit.SECONDS.sleep(3);
        stopWatch.stop();
        log.info("===> stop计时：{}",stopWatch.getTotalTimeMillis());
    }


    @Test
    public void test1() throws InterruptedException {
        Instant instant = Instant.now();
        log.info("{}",instant.getNano());

    }

    @Test
    public void Test1() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        TimeUnit.SECONDS.sleep(2);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());

        StopWatch stopWatch1 = new StopWatch();
        stopWatch1.start();
        TimeUnit.SECONDS.sleep(3);
        stopWatch.stop();
        stopWatch1.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
        System.out.println(stopWatch1.getTotalTimeSeconds());
    }

    @Test
    public void Test2() throws InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start();
        TimeUnit.SECONDS.sleep(1);
        sw.stop();
        sw.start();
        System.out.println(sw.getTotalTimeSeconds());
        TimeUnit.SECONDS.sleep(2);
        sw.stop();
        System.out.println(sw.getTotalTimeSeconds());
    }
}
