package com.wrh.lombokuse;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestLombok
 * @Description TODO
 * @Date 2020/10/27 11:29
 */
@Slf4j
public class TestLombok {
    @Test
    public void Test14() {
        boolean isDsEnd = false;
        boolean isYesterday = true;
        boolean isTheDayBeforeYesterday = false;
        boolean isLastWeekDay = true;
        boolean isYesterdayLastWeekDay = false;


        /*GameDayDataDateFlag flag = GameDayDataDateFlag.builder()
                .isDsEnd(isDsEnd)
                .isYesterday(isYesterday)
                .isTheDayBeforeYesterday(isTheDayBeforeYesterday)
                .isLastWeekDay(isLastWeekDay)
                .isYesterdayLastWeekDay(isYesterdayLastWeekDay)
                .build();
        System.out.println(flag);
        System.out.println(flag.isYesterday());

        boolean a = flag.isYesterday();
        System.out.println(a);*/
    }
}
