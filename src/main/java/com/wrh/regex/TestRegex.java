package com.wrh.regex;

import com.wrh.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestRegex
 * @Description TODO
 * @Date 2020/6/15 17:34
 */
@Slf4j
public class TestRegex {

    private static final String TIME_REGEX = "(?:^|&)now=(.*?)&";

    private static final String SEPERATOR_SINGLE = "`SEPERATOR`";

    private static final String SEPERATOR_SINGLE_2 = "@`SEPERATOR`@";

    public static long parseNow(String data) {
        return Long.parseLong(RegexUtils.getMatcherValue(TIME_REGEX, data));
    }

    @Test
    public void Test() {
        String data = "123123412&now=12344321&kdsljf";
        long timeStamp = parseNow(data);
        System.out.println(timeStamp);


        /*String data1 = "1231`SEPERATOR`23412&now=12344321&kdsljf";
        String[] d = data1.split(SEPERATOR_SINGLE);
        for (String s: d){
            System.out.println(s);
        }*/

        String data1 = "1231@`SEPERATOR`@23412&now=12344321&kdsljf";
        String[] d = data1.split(SEPERATOR_SINGLE_2);
        for (String s: d){
            System.out.println(s);
        }
    }


}
