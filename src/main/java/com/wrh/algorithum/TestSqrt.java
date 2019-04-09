package com.wrh.algorithum;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:51 2019/3/1 0001
 * @Modified By:
 */
public class TestSqrt {
    private static boolean judgeSqureSum(int c){
        int i = 0, j = (int) Math.sqrt(c);
        while( i <= j){
            int powSum = i *i +j*j;
            if(powSum == c){
                return true;
            }else if(powSum > c){
                j--;
            }else {
                i ++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int i = 5;
        System.out.println(judgeSqureSum(i));

        System.out.println(Math.sqrt(9));
    }

}
