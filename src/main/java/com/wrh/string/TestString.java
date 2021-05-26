package com.wrh.string;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

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
    
    @Test
    public void Test101() {
        String s1="abc";//字符串常量存储在字符串常量池里，目的是共享，并且字符串常量池中是不会存储相同内容的字符串的。
        //即s1,s2在栈中为一项，二者常量池指的是同一常量
        String s2="abc";
        String s3="111";
        System.out.println(s1==s2);//true，比较的是常量池中的地址
        s1="111";//value数组为final类型，所以不能对现有数组进行重新赋值，故新添加常量
        System.out.println(s1);//111，重写了指定内存区域赋值，指向字符串常量池中新添加的常量111
        System.out.println(s1==s2);//false，111和abc分别表示不同的内存区域，所以不相等。
        System.out.println(s1==s3);//true，指向相同的内存区域，所以相等
        System.out.println("*****************");
        String s4="abc";
        s4+="456";//经试验发现s4进行连接运算后，s2并未发生变化，所以s4依然是新创建的
        System.out.println(s4);//abc456，重写了指定内存区域赋值，重新指向内存区域赋值
        System.out.println(s2);//abc
        System.out.println("*****************");
        String s5="abc";//经试验发现调用了replace()方法后，s5值依旧保持不变，所以s6新创建的
        String s6=s5.replace("a","m");//重写了指定内存区域赋值，重新指向内存区域赋值
        System.out.println(s5);//abc
        System.out.println(s6);
        
    }
    
    @Test
    public void Test125() {
        String s3=new String("abc");//字符串非常量对象存储在堆中，s3,s4保存的地址值，是数据在堆空间中开辟以后对应的地址值
        String s4=new String("abc");
        String s1="abc";
        String s2="abc";
        System.out.println(s1==s2);//true
        System.out.println(s1==s3);//false
        System.out.println(s1==s4);//false
        System.out.println(s3==s4);//false
        
    }
    
    @Test
    public void Test138() {
        String s1 = "abc";
        String s2 = "1234";
        String s3 = "abc1234";
        String s4 = "abc" + "1234";
        String s5 = s1 + "1234";
        String s6 = "abc" + s2;
        String s7=s6.intern();
        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s5 == s6);//false
        System.out.println(s7== s3);//true
        /**
         * ①常量与常量的拼接结果还在常量池中
         ②拼接双方只要有一个是变量，结果就在堆中，就new了
         ③若拼接结果调用String中的intern()方法，能把返回值转换成常量池中存在的结果*/
        
    }

    @Test
    public void Test159() {
        getInt();
        return;
    }

    private int getInt() {
        int a = 10;
        try {
            System.out.println(a / 0);
            a = 20;
        } catch (ArithmeticException e) {
            a = 30;
            System.out.println("catch");
            return a;
        } finally {
            a = 40;
            //如果这样，就又重新形成了一条返回路径，由于只能通过1个return返回，所以这里直接返回40
            System.out.println("finally");
            return a;
        }
    }

    @Test
    public void Test182() {
        String str = "77||88";
        String[] s = str.split("\\|\\|");
        for (String s1 : s) {
            System.out.println(s1+" ");
        }

    }

    @Test
    public void Test192() {
        String name = "wrh";
        String title = "周报";
        StringJoiner joiner = new StringJoiner(File.separator);
        joiner.add("tmp").add(name).add(LocalDate.now().toString()).add(LocalTime.now().toString()+"-"+title+".csv");
        System.out.println("joiner.toString() = " + joiner.toString());


    }

    @Test
    public void Test209() {
        String title = "联运游戏-全部游戏-数据日报-(20210317)";
        String period = title.substring(title.lastIndexOf("(")+1).replace(")","");
        System.out.println(period);

    }

    @Test
    public void Test217() {
        String a = "w1";
        changeString(a);
        System.out.println("final a = " + a);

    }

    private void changeString(String a) {
        a = "w2";
        System.out.println("change a = " + a);
    }

    @Test
    public void Test230() {
        String t1 = "12:00";
        String t2 = "0:44";
        String t3 = "06:30";
        Integer a = Integer.valueOf(t1.replace(":",""));
        Integer b = Integer.valueOf(t2.replace(":",""));
        Integer c = Integer.valueOf(t3.replace(":",""));
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);


    }

    @Test
    public void Test245() {
        String t1 = "8200(12:01)";
        String a = t1.substring(t1.indexOf("(")+1,t1.length()-1);
        System.out.println("a = [" + a+"]");

    }

    @Test
    public void Test253() {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            Integer a = Integer.valueOf("130");
            Integer b = Integer.valueOf("129");
            if (a > b) {
                set.add("a>b");
            } else if (a < b) {
                set.add("a<b");
            } else {
                set.add("a==b");
            }
        }
        System.out.println("set = " + set);


    }

    @Test
    public void Test274() {
        Integer a = Integer.valueOf("1200");
        System.out.println(a);
        System.out.println("a.intValue() = " + a.intValue());

    }
}
