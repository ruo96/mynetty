package com.wrh.algorithum;

import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestMonkeySplitPeaches
 * @Description 猴子分桃子
 * @Date 2021/10/27 15:28
 */
public class TestMonkeySplitPeaches {

    public int sum_peach() {
        int i = 1;
        int peach =  1;
        int count = 1;
        while (i <= 5) {
            if (peach % 5 == 1) {
                peach = peach / 5 * 4;  // 分成五份，留下4份, 整除符号直接把那个多余的1去掉了
                i++;
            } else {
                i = 1;
                count ++;
                peach = count;
            }
        }
        return count;
    }

    @Test
    public void Test29() {
        System.out.println("sum_peach() = " + sum_peach());
    }
}
