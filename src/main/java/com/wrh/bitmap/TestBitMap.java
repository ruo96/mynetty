package com.wrh.bitmap;

import org.junit.Test;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:37 2019/1/8 0008
 * @Modified By:
 */
public class TestBitMap {

    //为了方便，假设数据是以数组的形式给我们的
    public static Set<Integer> test(int[] arr) {
        int j = 0;
        //用来把重复的数返回，存在Set里，这样避免返回重复的数。
        Set<Integer> output = new HashSet<>();
        BitSet bitSet = new BitSet(Integer.MAX_VALUE);
        int i = 0;
        while (i < arr.length) {
            int value = arr[i];
            //判断该数是否存在bitSet里
            if (bitSet.get(value)) {
                output.add(value);
            } else {
                bitSet.set(value, true);
            }
            i++;
        }
        System.out.println("bit TestSet: " + bitSet);
        return output;
    }
    //测试
    public static void main(String[] args) {
        int[] t = {1,2,3,4,5,6,7,8,3,4};
        Set<Integer> t2 = test(t);
        System.out.println(t2);
    }

    @Test
    public void Test43() {
        int a = 11;
        int b = a >> 3;
        System.out.println(b);

        int c = 1234123;
        int d = c % 8;
        int e = c & 0x07;
        System.out.println(d);
        System.out.println(e);

        int f = 7;
        int g = 1 << f;
        System.out.println(g);

    }
}
