package com.wrh.sort;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 8:42 2019/6/27 0027
 * @Modified By:
 */
public class TestSort {
    public static void main(String[] args) {

        int arr[] = {1,4,2,3,8};
        f(arr);
        return;
    }


    private static void f(int arr[]) {

        int[] temp = new int[21];
        for (int i = 0; i < arr.length; i++) {
            temp[arr[i]]++;
        }
        //顺序打印
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < temp[i]; j++) {
                System.out.println(i);
            }
        }
    }
}