package com.wrh.datetime;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:42 2018/12/13 0013
 * @Modified By:
 */
@Slf4j
public class TestDateTime {
    public static void main(String[] args) {
        Long beforeStamp = System.currentTimeMillis() - 100000000;

        String beforeStr = Long.toString(beforeStamp);
        System.out.println("beforeStr: " + beforeStr);

        /*如果判断某个日期和现在相差*/
        log.info("now : {}", LocalDate.now());

        Instant timestamp = Instant.now();
        /*比较两个日期的时间间隔*/


        Period period = Period.between(LocalDate.now(),LocalDate.parse(timestamp.toString()));
        log.info("相差:{}" , period.getDays());

    }
}
