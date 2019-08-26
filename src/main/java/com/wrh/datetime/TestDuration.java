package com.wrh.datetime;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 7:52 2019/6/20 0020
 * @Modified By:
 */
public class TestDuration {
    public static void main(String[] args) throws InterruptedException {

        LocalDateTime t1 = LocalDateTime.now();
        Thread.sleep(2000);
        LocalDateTime t2 = LocalDateTime.now();

        Duration duration = Duration.between(t1,t2);
        duration.toDays();
        duration.toHours();
        duration.toMinutes();
        System.out.println(duration.toMillis());
    }
}
