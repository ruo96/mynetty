package com.wrh.basic.stringtest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname TestString
 * @Description TODO
 * @Date 2020/1/13 16:05
 * @Created by wuruohong
 */
@Slf4j
public class TestString {

    @Test
    public void Test() {
        String formatStr = "%s_%s_user_allgames_online";
        String a = null;
        String b = null;
        String newStr = String.format(formatStr,a,b);
        System.out.println(newStr);
    }

    @Test
    public void Test1() {

        String a = "123";
        List<String> b = new ArrayList<>();
        b.add(a);
        a = "456";
        b.add(a);
        System.out.println(b);
    }
}
