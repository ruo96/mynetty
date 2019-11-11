package com.wrh.datetime.jodaTimeTest;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:06 2019/10/23 0023
 * @Modified By:
 */
@Slf4j
public class TestJodaTime {
    @Test
    public void test(){
        DateTime time = new DateTime().withTime(15,0,0,0);
        log.info("==-=> time:{}",time);
        log.info("==-=> time:{}",time.plusDays(1));
    }
}
