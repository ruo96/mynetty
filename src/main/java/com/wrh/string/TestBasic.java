package com.wrh.string;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:09 2018/11/2 0002
 * @Modified By:
 */
@Slf4j
public class TestBasic {

    public static void main(String[] args) throws UnsupportedEncodingException {
        /*int a = 10 >>1;
        int b = a++;
        int c = ++a;
        int d = b * a++;
        System.out.println(a);   //
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);*/


        String i = "123吴若鸿";
        System.out.println("长度是:" + i.length());
        for(int k =1; k<(i.length()-1); k++){

            log.info("长度{}是:{}" ,k,StringUtils.substring(i,0,k));
        }
        log.info("是:{}" ,StringUtils.substring(i,0,5));


        String ret = "2|8820170000000107863|0001|1|0210003001|                                          CCKS测试人员黄飞鸿|1,              123420181102170800|8820170000000107863|----------|01|                      autumn1328|BJTBIZ|F000000001800109|03|8820170000000107863|0400|ccks联机|1362|0210003001|----------|20170613|20180623|000|999999999900|999999999900|999999999900|                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              F000000001800109        02100030010001.csc.so";
        String ret1 = "2|8820170000000107863|0001|1|0210003001|                                          CCKS测试人员黄飞鸿|1,              123420181102170800|8820170000000107863|----------|01|                      autumn1328|BJTBIZ|F000000001800109|03|8820170000000107863|0400|ccks联机|1362|0210003001|----------|20170613|20180623|000|999999999900|999999999900|999999999900|                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ";

        String ret2 = "12345asdfg";
/*
        String newRet = ret.substring(0,989);
        log.info("new Ret is : {}",newRet);
        String aid = ret.substring(990,1013);
        log.info("aid is : {}",aid);


        byte[] fullResult = ret.getBytes("GBK");
        log.info("返回总长度:{}",ret.length());*/

        log.info("返回ret 总长度:{}",ret.length());
        byte[] fullResult = ret.getBytes("GBK");
        log.info("返回ret getbyte ghk 之后,length :　{}",fullResult.length);

        byte[] fullResult1 = ret.getBytes();
        log.info("返回ret getbyte utf 之后,length :　{}",fullResult1.length);


        String mm = "123   ";
        byte[] mmbyte = mm.getBytes();
        String mmStr = new String(mmbyte);
//        String mmStr = String.valueOf(mmbyte);
        log.info("mmStr is :{}",mmStr);
        log.info("mmStr length is :{}",mmStr.length());

        log.info("trim is : {}",mmStr.trim());
        log.info("trim length is : {}",mmStr.trim().length());




    }
}