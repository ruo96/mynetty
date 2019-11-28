package com.wrh.netty.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:35 2019/11/14 0014
 * @Modified By:
 */
@Slf4j
public class BasicConcept {

    @Test
    public void test1(){
        /*ByteBuf buf = ByteBufAllocator;

        CompositeByteBuf*/

    }

    @Test
    public void test2(){
        int i = -54;
        int b = i - 1;
        b |= b >>>1;
        b |= b >>>2;
        b |= b >>>4;
        b |= b >>>8;
        b |= b >>>16;
        System.out.println(b+1);

        int j = 14;
        int k = -14;
        System.out.println(j>>>1);
        System.out.println(k>>1);
    }

    @Test
    public void test3(){
        int i = 999999999;
        int b = i - 1;
        b |= b >>1;
        b |= b >>2;
        b |= b >>4;
        b |= b >>8;
        b |= b >>16;
        System.out.println(b+1);
    }

    @Test
    public void test4(){
        String a = "0";
        System.out.println(a.hashCode());

        char[] achar = a.toCharArray();
        System.out.println("legnth: " + achar.length);
        System.out.println(achar[0] + 31);
        System.out.println(achar[0]);


        /*int h = 0;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;*/
        HashMap<String, String> map = new HashMap<>();

    }

    @Test
    public void test5(){
        String a = "abcd";
        String b = "dcba";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        String c = "";
        System.out.println(c.hashCode());
    }

    @Test
    public void test6(){
        String a = "123";
        System.out.println(a.hashCode());
    }

    @Test
    public void test7(){
        char a = '1';
        System.out.println(1+a);

        Integer i = 12345;
        System.out.println(i.hashCode());
    }
}
