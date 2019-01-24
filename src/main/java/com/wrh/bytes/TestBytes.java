package com.wrh.bytes;

import com.alibaba.fastjson.JSON;
import com.google.common.primitives.Bytes;

import java.util.Arrays;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:44 2018/10/29 0029
 * @Modified By:
 */
public class TestBytes {

    public static void main(String[] args) {
        byte[] b = new byte[0];
        System.out.println("size is :" + b.length);

        byte[] c = new byte[1];
        System.out.println("size is :" + c.length);

        String a = "123";
        byte[] ab = a.getBytes();

        String d = "456";
        byte[] dd = d.getBytes();

        byte[] bytes1 = Bytes.concat(ab, b, dd);
        byte[] bytes2 = Bytes.concat(ab,  dd);
        System.out.println("bytes1: "+ Arrays.toString(bytes1));
        System.out.println("bytes1 length: "+ bytes1.length);
        System.out.println("bytes2: "+ Arrays.toString(bytes2));
        System.out.println("bytes2 length: "+ bytes2.length);

        Integer i1 = -129;
        Integer i2 = -129;
        System.out.println("i1 == i2: " + (i1 == i2));


        int[] test = new int[]{1,2,3,4};
        List list = Arrays.asList(test);
        System.out.println("list size is : " + list.size());
        System.out.println(JSON.toJSONString(list));
        System.out.println(list.get(0).getClass());

    }
}
