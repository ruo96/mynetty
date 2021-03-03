package com.wrh.random;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:13 2019/6/4 0004
 * @Modified By:
 */
@Slf4j
public class TestRandom {
    public static void main(String[] args) {
        Random i = new Random();
        System.out.println(i.nextInt());
        System.out.println(i.nextInt());
        System.out.println(i.nextInt());
        System.out.println(i.nextInt());

        int j = 100;
        System.out.println(j>>1);
        System.out.println(j>>>1);

        int k = -100;
        System.out.println(k>>1);
        System.out.println(k>>>1);
    }

    @Test
    public void Test29() {
        for (int i = 0; i < 10; i++) {
            int random = new Random().nextInt(100);
            System.out.println(random);
        }
    }

    @Test
    public void Test41() {
        Random random = new Random(4);
        System.out.println(random.nextInt(10));

    }

    /**
     * 4位随机码
     * @return
     */
    public static String runNumber() {
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        System.out.println(sb.toString());
        String code = sb.toString();
        return code;
    }

    @Test
    public void Test61() {
        for (int i = 0; i < 10; i++) {
            System.out.println("runNumber() = " + runNumber());

        }
    }
    @Test
    public void Test68() {
        for (int i = 0; i < 10; i++) {
            System.out.println("new Random().nextInt(5) = " + new Random().nextInt(5));
        }

    }
}
