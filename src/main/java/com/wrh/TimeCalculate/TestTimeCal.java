package com.wrh.TimeCalculate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

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
        log.info("===> stop计时：{}",stopWatch.getTotalTimeSeconds());
    }
}
