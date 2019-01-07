package com.wrh.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:48 2018/11/7 0007
 * @Modified By:
 */
public class TestCurrentHashMap {
    public static void main(String[] args) {
        byte[] a = null;

        if( a== null){

            System.out.println("a is null");
        }

        Map<String, Long> sysReqNo = new ConcurrentHashMap<>();
        System.out.println(sysReqNo.size());
        sysReqNo.put("req",1L);
        System.out.println(sysReqNo.size());
        sysReqNo.put("req",2L);
        System.out.println(sysReqNo.size());
        System.out.println(sysReqNo.get("req"));


        String a1 = "0000100002";
        long b1 = Long.valueOf(a1);
        System.out.println(b1);

    }
}
