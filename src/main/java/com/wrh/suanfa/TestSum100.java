package com.wrh.suanfa;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:43 2019/1/21 0021
 * @Modified By:
 */
public class TestSum100 {
    /*计算1+2+3+100*/
    public long temp = 0;
    public long sum_solution(int n){
        complex(n);
        return temp;
    }

    public long complex(int n){
        boolean flag = (n-1) >=0 && (temp = temp + n ) > 0 && complex(n-1) > 0;
        return temp;
    }

    public static void main(String[] args) {

        /*这种方式容易栈溢出*/
        /*long startTime = System.currentTimeMillis();
        TestSum100 testSum100 = new TestSum100();
        long i = testSum100.sum_solution(100000);
        long endTime = System.currentTimeMillis();
        System.out.println("计算1+2+...+3 = " + i ) ;
        System.out.println("耗时 = " + (endTime - startTime) ) ;*/


        long i = 0;

        /*如果换成另外一种算法*/
        long startTime1 = System.currentTimeMillis();
        long j ;
        for( j = 100000000; j>0 ; j--){
            i = i+j;
            j--;
        }
        System.out.println("i is :  " + i);

        long endTime1 = System.currentTimeMillis();
        System.out.println("耗时 = " + (endTime1 - startTime1) ) ;
    }
}
