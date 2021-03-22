package com.wrh.basicUse;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.wrh.basicUse.vo.OnLineCleanParamDto;
import com.wrh.entity.Person;
import com.wrh.functionInterfaceTest.Student;
import com.wrh.utils.Md5Utils;
import com.wrh.utils.ObjectCheckHandleUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Classname TestString
 * @Description TODO
 * @Date 2020/1/9 16:59
 * @Created by wuruohong
 */
@Slf4j
public class TestString {
    @Test
    public void Test() {
        String uid = "123";
        String inter = null;
        String ds = "456";

        String uidRowKey = Joiner.on("_").skipNulls().join(uid, inter,ds);
        log.info(" result: {}",uidRowKey);
    }

    @Test
    public void Test231() {
        String i = "user_online_gameid_";
        String data = "user_online_gameid_20200316_1234_5678";
        log.info("{}",i.length());
        log.info("data: {}, substring:{}",data, StringUtils.substring(data,0,i.length()));
        log.info("data: {}, substring:{}",data, StringUtils.substring(data,0,2));
//        System.out.println(StringUtils.substring(data,0,i.length()));
    }

    @Test
    public void Test22() {

        String a = "";
//        Long b = Long.valueOf(a);
        Long c = Long.valueOf(null);
//        System.out.println(b);
        System.out.println(c);
    }

    @Test
    public void Test23() {
        String a = "12345654634534512312312312312";
        Long start = System.currentTimeMillis();
        int length;
        for (int i = 0; i < 100000000; i++) {
            if(a.length() >20 ){

            }
        }
        Long end = System.currentTimeMillis();
        System.out.println(end - start);

        Long start1 = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            if(StringUtils.length(a) >20 ){

            }
        }
        Long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);

    }

    /**
     * 测试md5工具类
     */
    @Test
    public void Test1() {
        String a = "123";
        String md5Hash = Md5Utils.encrypt(a);
        log.info(" result: {}",md5Hash);

    }

    @Test
    public void Test2() {
        OnLineCleanParamDto student = new OnLineCleanParamDto();
        student.setTourMark("123   ");
        student.setUid("234  ");
        student.setBuvid("21312  ");
        student.setGameId(" 123123s ");
        student.setServerRecTime("dsfs ");
        student.setIntervalTime("  adfas");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        /*for (int i = 0; i < 10000; i++) {

            boolean flag = ObjectCheckHandleUtils.handle(student);
        }*/
        boolean flag = ObjectCheckHandleUtils.handle(student);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
        System.out.println(flag);
        System.out.println(JSON.toJSONString(student));
    }

    @Test
    public void Test3() {
        String[] a = new String[2];
        Object[] b = a;
        a[0] = "hi";
        b[1] = Integer.valueOf(42);
    }

    @Test
    public void Test4() {
        String a = null;
        String b = "1";
        a = "12";
        System.out.println(Integer.valueOf(a));
        System.out.println(Integer.valueOf(b));
    }

    @Test
    public void Test128() {
        String str = String.format("format_%s", 1);
        System.out.println(str);

    }

    @Test
    public void Test135() {
        double a = 0.0;
        System.out.println(a);
    }

    @Test
    public void Test141() {
        int i = 1;
        i = i++;
        System.out.println(i);

    }

    @Test
    public void Test149() {
        String s = "abc";
        changeString(s);
        System.out.println(s);
    }

    private void changeString(String s) {
        s = "123";
    }

    public static final String OFFLINE_TOTAL_KPI = "offline_total_kpi_%s";
    @Test
    public void Test160() {
        Integer i = 100;
        System.out.println(String.format(OFFLINE_TOTAL_KPI, i));

    }

    @Test
    public void Test168() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println("str1.intern() == str1 = " + (str1.intern() == str1));// 首次出现的字符串，则intern返回的就是堆中的引用，但是如果非首次出现，那么就不一样对象

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println("str2.intern() == str2 = " + (str2.intern() == str2));

        String str3 = new StringBuilder("boo").append("lean").toString();
        System.out.println("str3.intern() == str3 = " + (str3.intern() == str3));


    }

    @Test
    public void Test182() {
        /*int i  = 2;
        int j = --i;
        System.out.println(i);
        System.out.println(j);*/

        Integer[] a = {1,2,3,4,5};
        int index = 4;
        Integer i = a[--index];
        System.out.println("1--i = " + i);
        System.out.println("1--Arrays.toString(a) = " + Arrays.toString(a));
        a[index] = null;
        System.out.println("2--Arrays.toString(a) = " + Arrays.toString(a));
        System.out.println("2--i = " + i);

    }

    @Test
    public void Test202() {
        String str = "UTC+8";
        System.out.println("str.substring(3) = " + str.substring(4));

    }

    @Test
    public void Test209() {
        String ds = "year";
        System.out.println("getStrSwitch(ds) = " + getStrSwitch(ds));

    }

    String getStrSwitch(String ds) {
        String ds1 ;
        switch (ds) {
            case "day":
                System.out.println("is day");
                ds1 = "day";
                break;
            case "year":
                System.out.println("is year");
                ds1 = "year";
                break;
            default:
                return "default";
        }
        return ds1;
    }

    @Test
    public void Test233() throws IOException {
        Person s = new Person();
        s.setName("w1");
        String jsonStr = JSON.toJSONString(s);
        System.out.println("jsonStr = " + jsonStr);
        Person s1 = new ObjectMapper().readValue(jsonStr, Person.class);
        System.out.println("s1 = " + s1);

    }
}
