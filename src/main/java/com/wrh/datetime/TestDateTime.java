package com.wrh.datetime;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.time.*;
import java.util.Calendar;
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

/*        Instant timestamp = Instant.now();
        *//*比较两个日期的时间间隔*//*

        Period period = Period.between(LocalDate.now(),LocalDate.parse(timestamp.toString()));
        log.info("相差:{}" , period.getDays());*/

        System.out.println("-------------------3 methods-----------------------");
        System.out.println(System.currentTimeMillis());
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(Clock.systemDefaultZone().millis());


        System.out.println(LocalDateTime.now());


        String a = "1a";
        int i = Integer.valueOf(a);
        System.out.println("i is : " + i);





    }
}
