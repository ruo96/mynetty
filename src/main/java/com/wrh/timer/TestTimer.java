package com.wrh.timer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Timer;

/**
 * @author wuruohong
 * @Classname TestTimer
 * @Description TODO
 * @Date 2020/7/2 15:52
 */
@Slf4j
public class TestTimer {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    @Test
    public void Test14() {
        System.out.println(getYesterdayByDs("2020-07-03"));
    }

    public static String getYesterdayByDs(String ds) {
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(DATE_FORMAT));
        return date.minusDays(1).toString();
    }
}
