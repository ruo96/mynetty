package com.wrh.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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



}
