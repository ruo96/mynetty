package com.wrh.math;

import com.google.common.util.concurrent.RateLimiter;
import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:58 2019/6/11 0011
 * @Modified By:
 */
@Slf4j
public class TestInteger {
    public static void main(String[] args) {
        Integer i1 = 123;
        Integer i2 = 123;
        System.out.println(i1 == i2);

        Integer i3 = 127;
        Integer i4 = 127;
        System.out.println(i3 == i4);

        Double i5 = 1.0;
        Double i6 = 1.0;
        System.out.println(i5 == i6);

        Integer i7 = 40;
        Integer i8 = new Integer(40);
        System.out.println(i7 == i8);



    }

    @Test
    public void test(){
        int i = 1;
        System.out.println(i+++i++);
        System.out.println(i);

        int j = 1;
        System.out.println(j++ + ++j);

    }


    @Test
    public void test1(){
        int i = 1;
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i++);


        int j = 1;
        System.out.println(++j);
        System.out.println(++j);
        System.out.println(++j);
        System.out.println(++j);

        Integer i1 = 250;
        Integer i2 = 250;
        System.out.println(i1 == i2);

        Integer i3 = 100;
        Integer i4 = 100;
        System.out.println(i3 == i4);


    }

    @Test
    public void test2(){
        int i = getResult();
        System.out.println(i);

        Integer j = getResult();
        System.out.println(j);


    }

    private int getResult() {
        try {
            Thread.sleep(1);
            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return 2;
        }
    }

    @Test
    public void test3(){
        boolean groupId = checkGroupId("abc1");
        System.out.println(groupId);


    }

    private boolean checkGroupId(String collectGroupId) {
        boolean result = false;

        int groupId;
        try {
            groupId = Integer.parseInt(collectGroupId);
        } catch (NumberFormatException e) {
            log.info("===> 库跑批，组id{}解析异常， e: {}", collectGroupId, e);
            return result;
        }

        log.info("=====> ");
        if( groupId > 10){
            return true;
        }else {
            return false;
        }

    }

    @Test
    public void test5(){
        Integer i1 = 130;
        Integer i2 = 130;

        if(i1 == i2){
            log.info("i1==i2");
        }

        if(i1.equals(i2)){
            log.info("i1.equals(i2)");
        }

    }

    @Test
    public void test6(){
        int i = 0;
        int j = i++;
        int k =  ++i;
        log.info("===> {}",i);
        log.info("===> {}",j);
        log.info("===> {}",k);

    }

    @Test
    public void test7(){
//        byte[] a = {0x11};
        byte x[] = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x11};
        long b = longFrom8Bytes(x,0,false);
        log.info("b:{}",b);

        long c = bytesTolong(x,0,false);
        log.info("c:{}",c);

    }


    /**
     * 将字节数组转为long<br>
     * 如果input为null,或offset指定的剩余数组长度不足8字节则抛出异常
     * @param input
     * @param offset 起始偏移量
     * @param littleEndian 输入数组是否小端模式
     * @return
     */public static long longFrom8Bytes(byte[] input, int offset, Boolean littleEndian){
        long value=0;
        // 循环读取每个字节通过移位运算完成long的8个字节拼装
        for (int  count=0;count<8;++count){
            int shift=(littleEndian?count:(7-count))<<3;
            value |=((long)0xff<< shift) & ((long)input[offset+count] << shift);
        }
        return value;}

    /**
     * 利用 {@link java.nio.ByteBuffer}实现byte[]转long
     * @param input
     * @param offset
     * @param littleEndian 输入数组是否小端模式
     * @return
     */public static long bytesTolong(byte[] input, int offset, Boolean littleEndian) {
        // 将byte[] 封装为 ByteBuffer
        ByteBuffer buffer = ByteBuffer.wrap(input,offset,8);
        if(littleEndian){
            // ByteBuffer.order(ByteOrder) 方法指定字节序,即大小端模式(BIG_ENDIAN/LITTLE_ENDIAN)
            // ByteBuffer 默认为大端(BIG_ENDIAN)模式
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        return buffer.getLong();}

     @Test
     public void test8(){
         int i = 1;
         int j = 2;
         int k = i|j;
         System.out.println(k);
     }

     @Test
     public void test9() throws UnknownHostException {
         String addr = InetAddress.getLocalHost().getHostAddress();
         log.info("addr: {}",addr);

         String getHostName = InetAddress.getLocalHost().getHostName();
         String getAddress = String.valueOf(InetAddress.getLocalHost().getAddress());
         log.info("getHostName: {}",getHostName);
         log.info("getAddress: {}",getAddress);

         RateLimiter limiter = RateLimiter.create(2);
     }

     @Test
     public void Test10() {
         Student student = new Student();
         student.setName("w1");
         student.setId(0);
         student.setGrade(10);

         Student student1 = new Student();
         student1.setName("w1");
         student1.setId(0);
         student1.setGrade(20);

         List<Student> students = new ArrayList<>();
         students.add(student);
         students.add(student1);

         long sum = students.stream().filter(e->e.getGrade()>100).mapToInt(Student::getGrade).sum();
         System.out.println(sum);

         List<Student> students1 = new ArrayList<>();
         long sum1 = students1.stream().filter(e->e.getGrade()>100).mapToInt(Student::getGrade).sum();
         System.out.println(sum1);

     }

     @Test
     public void Test11() {
         double a = 1.5;
         System.out.println((long)a);

         System.out.println( (long)(2/1.5+0.5));
         System.out.println( 2/1.5);
     }

     @Test
     public void Test12() {
         double a =  223.0;
         System.out.println(a);
         DecimalFormat df = new DecimalFormat("#");
         System.out.println(df.format(a));
         System.out.println(Double.parseDouble(df.format(a)));
     }

     @Test
     public void Test13() {
         long before = Runtime.getRuntime().freeMemory();
         Map<String, Integer> map = new HashMap<>(1000000);
         /*for (int i = 0; i < 1000000 ; i++) {
             map.put("abcdefghijklmnopqrstuvwxyz" + i, i );
         }*/
//         map = null;
//         map.clear();
         System.out.println(map);
//         System.gc();
         long after = Runtime.getRuntime().freeMemory();
         System.out.println(before/1024/1024);
         System.out.println(after/1024/1024);
         System.out.println((before-after)/1024/1024);

     }

     @Test
     public void Test14() {
         double pi = 3.1415927;//圆周率
//取一位整数
         System.out.println(new DecimalFormat("0").format(pi));//3
//取一位整数和两位小数
         System.out.println(new DecimalFormat("0.00").format(pi));//3.14
//取两位整数和三位小数，整数不足部分以0填补。
         System.out.println(new DecimalFormat("00.000").format(pi));// 03.142
//取所有整数部分
         System.out.println(new DecimalFormat("#").format(pi));//3
//以百分比方式计数，并取两位小数
         System.out.println(new DecimalFormat("#.##%").format(pi));//314.16%

         /**
          * 上面的代码就是网上很经典的案例，下面我们来分析另外的一个值
          */
         pi=12.34567;
//取一位整数
         System.out.println(new DecimalFormat("0").format(pi));//12
//取一位整数和两位小数
         System.out.println(new DecimalFormat("0.00").format(pi));//12.35
//取两位整数和三位小数，整数不足部分以0填补。
         System.out.println(new DecimalFormat("00.000").format(pi));// 12.346
//取所有整数部分
         System.out.println(new DecimalFormat("#").format(pi));//12
//以百分比方式计数，并取两位小数
         System.out.println(new DecimalFormat("#.##%").format(pi));//1234.57%

/**
 * 扩展，如果是其他的数字会是下面的效果
 */
         pi=12.34;
//整数
         System.out.println(new DecimalFormat("6").format(pi));//612
         System.out.println(new DecimalFormat("60").format(pi));//612
         System.out.println(new DecimalFormat("06").format(pi));//126
         System.out.println(new DecimalFormat("00600").format(pi));//00126
         System.out.println(new DecimalFormat("#####60000").format(pi));//00126
//小数
         System.out.println(new DecimalFormat(".6").format(pi));//12.6
         System.out.println(new DecimalFormat(".06").format(pi));//12.36
         System.out.println(new DecimalFormat(".60").format(pi));//12.36
         System.out.println(new DecimalFormat(".0600").format(pi));//12.3406
         System.out.println(new DecimalFormat(".6000").format(pi));//12.3406
         System.out.println(new DecimalFormat(".600000##").format(pi));//12.340006
     }

     @Test
     public void Test15() {
         Integer a = 222;
         Integer b = 222;
         if(a == b){
             System.out.println("equal");
         }
     }

     @Test
     public void Test16() {
         Long a = 1000L;
         double b = 0.2556;
         Long c = (long)(a *b);
         System.out.println(c);
     }

     @Test
     public void Test17() {
         System.out.println(lowZeros(6));
         System.out.println(lowZeros(10));
         System.out.println(lowZeros(22));
         System.out.println(lowZeros(45));
     }
     private int lowZeros(long value) {
         int i =0;
         for (; i < 32; i++) {
             if(value >> i << i != value ){
                 break;
             }
         }
         return i - 1;

     }

     @Test
     public void Test372() {
         int i = 1;
         int b = (++i) + (++i);
         System.out.println(b);

     }

     @Test
     public void Test380() {
         double a = 0.1427;
         double b = 0.2084;
         double c = a + b;
         System.out.println(c);
         double d = 1 - c;
         System.out.println(d);
         System.out.println(c + d);

         BigDecimal a1 = new BigDecimal("0.1427");
         BigDecimal b1 = new BigDecimal("0.2084");
         BigDecimal c1 = a1.add(b1);
         System.out.println(c1.doubleValue());

         BigDecimal d1 = new BigDecimal("1").subtract(c1);
         System.out.println(d1.doubleValue());

     }

     @Test
     public void Test401() {
         int i = 1;
         i = i++;
         int j = i++;
         int k = i + ++i * i++;
         System.out.println(i);
         System.out.println(j);
         System.out.println(k);
     }

     @Test
     public void Test412() {
        Long a = 0L;
        if(a.equals(0)){
            System.out.println("equal");
        }else {
            System.out.println("not equal");
        }

        Integer a1 = 222;
        Integer b1 = 222;
        if(a1.equals(b1)){
            System.out.println("equal integer");
        }else {
            System.out.println("not equal integer");
        }
     }

     @Test
     public void Test431() {
         int a = 0x12;
         System.out.println(a);
         int b = 012;
         System.out.println(b);

     }

     @Test
     public void Test440() {
         int i = 60;
         int j = i >>> 4;
         System.out.println(j);
         System.out.println(Integer.toBinaryString(i));

         int[][] arr = {{1,2},{3,4}};
         System.out.println(Arrays.toString(arr));

     }

     @Test
     public void Test449() {
         Map<Integer,List<Student>> map = new HashMap<>();
         Long total = map.getOrDefault(1,new ArrayList<>()).stream().mapToLong(Student::getGrade).sum();
         System.out.println(total);

     }



}
