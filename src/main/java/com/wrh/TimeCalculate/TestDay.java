package com.wrh.TimeCalculate;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.wrh.TimeCalculate.DateTimeUtil.yyyyMMdd;

/**
 * @author wuruohong
 * @Classname TestDay
 * @Description TODO
 * @Date 2020/5/26 15:40
 */
@Slf4j
public class TestDay {
    @Test
    public void Test() {

        String date = "2020-01-02";

        System.out.println(getDaysByDate(date));
    }
    public static int getDaysByDate(String dateStr) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(yyyyMMdd);
        LocalDate date = LocalDate.parse(dateStr, fmt);
        return date.getDayOfYear();
    }

    public List<String> getLastWeekDsList(){
        List<String> dsList = new ArrayList<>();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        dsList.add(now.format(pattern));
        dsList.add(now.minusDays(1).format(pattern));
        dsList.add(now.minusDays(2).format(pattern));
        dsList.add(now.minusDays(3).format(pattern));
        dsList.add(now.minusDays(4).format(pattern));
        dsList.add(now.minusDays(5).format(pattern));
        dsList.add(now.minusDays(6).format(pattern));
        dsList.add(now.minusDays(7).format(pattern));
        return dsList;
    }

    public List<String> getLastWeekDsListByDate(String date){
        List<String> dsList = new ArrayList<>();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.parse(date, pattern);
        dsList.add(now.format(pattern));
        dsList.add(now.minusDays(1).format(pattern));
        dsList.add(now.minusDays(2).format(pattern));
        dsList.add(now.minusDays(3).format(pattern));
        dsList.add(now.minusDays(4).format(pattern));
        dsList.add(now.minusDays(5).format(pattern));
        dsList.add(now.minusDays(6).format(pattern));
        dsList.add(now.minusDays(7).format(pattern));
        return dsList;
    }
    
    @Test
    public void Test1() {
        System.out.println(getLastWeekDsList());
        System.out.println("------------------------------");
        System.out.println(getLastWeekDsListByDate("2020-05-02"));
    }

    @Test
    public void Test2() {
        System.out.println(LocalDate.now().getDayOfYear());

        System.out.println(LocalDate.parse("2020-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")).getDayOfYear());

        System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        Integer days = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        System.out.println("today : "+days);
    }

}
