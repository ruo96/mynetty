package com.wrh.string;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:26 2018/9/28 0028
 * @Modified By:
 */
@Slf4j
public class TestString {

    public static void main(String[] args) throws UnsupportedEncodingException {
        /*String origin = new String("123");
        String s = "123";
        String s1 = "123".intern();
        String s2 = new String("123");
        String s3 = new String("123");
        String s4 = new String("123").intern();

        System.out.println("origin: "+ System.identityHashCode(origin));
        System.out.println("s: "+ System.identityHashCode(s));
        System.out.println("s1: "+ System.identityHashCode(s1));
        System.out.println("s2: "+ System.identityHashCode(s2));
        System.out.println("s3: "+ System.identityHashCode(s3));
        System.out.println("s4: "+ System.identityHashCode(s4));
        System.out.println("s == s1? " + (s == s1));
        System.out.println("s == s1? " + s == s1);
        System.out.println("s .equals s1? " + s .equals(s1) );

        StringBuffer sb ;*/


       /* String m = new String("1");
        m.intern();
        String m2 = "1";
        System.out.println(m == m2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);*/


        /*Long a = new Long(3);
        Long b = new Long(3);
        System.out.println(a.longValue()==b.longValue());
        System.out.println(a==b);*/
        String mm = "中国";
        System.out.println("中国的长度为: {}" + mm.length());

        String i = "中国人名就123";
        System.out.println("汉字的一个长度为:{}"+i.length());

        int valLen = i.getBytes("gbk").length;
        System.out.println("长度为:{}"+valLen);

        int len = 10;


        String val = StringUtils.leftPad(i, len, ' ');
        log.info("补了之后为:[{}]",val);
        log.info("补了之后的长度为:[{}]",val.length());

        byte[] valBytes = val.getBytes("gbk");
        log.info("getBytes 后的长度为:{}",valBytes.length);
        log.info("getBytes 后的数据为:{}", Arrays.asList(valBytes));

        byte[] valBytes1 = val.getBytes();
        log.info("getBytes 后的长度为:{}",valBytes1.length);
        log.info("getBytes 后的数据为:{}", Arrays.asList(valBytes1));


    }
    @Test
    public void Test1() {
        String a = "a^b^c";
        String[] arr = a.split("^");
        for(String s: arr){

            System.out.println(s);
        }
    }

    @Test
    public void Test92() {
        String a = "wrh";
        String b = a.concat("123");
        System.out.println(a);
        System.out.println(b);

    }
}
