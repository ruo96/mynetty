package com.wrh.arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author wuruohong
 * @Classname TestArrays
 * @Description TODO
 * @Date 2020/7/3 21:03
 */
@Slf4j
public class TestArrays {

    /**
     * 数组初始化
     * @return
     */
    public int[] getArrays(){
        int[] array = new int[10];
        Arrays.setAll(array, i -> i+1);
        return array;
    }

    /**
     * 数组取流
     */
    @Test
    public void Test15() {
        int[] array = getArrays();
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.stream(array, 0, 5).sum());

    }

    /**
     * 数组累加
     */
    @Test
    public void Test32() {
        int[] array = getArrays();
        System.out.println(Arrays.toString(array));
        Arrays.parallelPrefix(array, (left, right) -> left + right);
        System.out.println(Arrays.toString(array));

    }

    /**
     * 数组获取最大值  stream
     */
    @Test
    public void Test51() {
        int[] arrayInt = {15,2,23,3};
        System.out.println(Arrays.stream(arrayInt).max().getAsInt());

    }

    @Test
    public void Test61() {
        int[] arrayInt = {15,2,23,3};
        System.out.println(Arrays.toString(arrayInt));
        selectSort(arrayInt);
        System.out.println(Arrays.toString(arrayInt));

    }

    private void selectSort(int[] arrayInt) {
        arrayInt[0] = 999;
    }

    @Test
    public void Test74() {
        int[] arrayInt = {15,2,23,3};
        int index = Arrays.binarySearch(arrayInt,0);
        /** 如果为正，代表是真实存在的索引， 如果为负值，代表-1后的真实插入位置*/
        System.out.println(index);

    }

}
