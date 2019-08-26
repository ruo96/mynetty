package com.wrh.datetime.testYesterday;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:45 2019/4/28 0028
 * @Modified By:
 */
public class TestYesterday {

    private static final String dateFormat = "yyyy-MM-dd";

    public static void main(String[] args) {
        String date = LocalDateTime.now().plusDays(-1).format(DateTimeFormatter.ofPattern(dateFormat));
        System.out.println(date);

        System.out.println("now: " + LocalDateTime.now());
    }
}
