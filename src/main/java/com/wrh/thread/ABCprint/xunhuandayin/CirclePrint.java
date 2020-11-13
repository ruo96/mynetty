package com.wrh.thread.ABCprint.xunhuandayin;

import org.junit.Test;

/**
 * @author wuruohong
 * @Classname CirclePrint
 * @Description TODO
 * @Date 2020/11/4 15:00
 */
public class CirclePrint {

    /**
     * 总结下阿里最喜欢问的多个线程顺序打印问题，我遇到的是机试，直接写出运行。同类型的题目有很多，比如：
     * 1. 三个线程分别打印 A，B，C，要求这三个线程一起运行，打印 n 次，输出形如“ABCABCABC....”的字符串
     * 2. 两个线程交替打印 0~100 的奇偶数
     * 3. 通过 N 个线程顺序循环打印从 0 至 100
     * 4. 多线程按顺序调用，A->B->C，AA 打印 5 次，BB 打印10 次，CC 打印 15 次，重复 10 次
     * 5. 用两个线程，一个输出字母，一个输出数字，交替输出 1A2B3C4D...26Z
     * 其实这类题目考察的都是线程间的通信问题
     * 分别放在外面的几个类文件中
     */
    @Test
    public void Test58() {
        char a = (char)('A'+1);
        System.out.println(a);
        System.out.println('A'+1);

    }
}
