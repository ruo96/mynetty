package com.wrh.algorithum.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author wuruohong
 * @Classname TestSorted
 * @Description TODO
 * @Date 2020/12/18 11:11
 */
@Slf4j
public class TestSorted {

    @Test
    public void Test15() {
        int[] a = {1,2,3,4,5,6,7,11,13,15,33};
        System.out.println(Arrays.toString(a));
//        sortodd(a);
//        sortoddWith2Pointers(a);
        exchange(a);
        System.out.println(Arrays.toString(a));
    }

    private void sortodd(int[] a){
        int length = a.length;
        int i = 0;
        int j = 1;
        /** 连续偶数的情况下应该怎么办*/
        while (i < length-1 && j < length) {
            if((a[i] % 2 ==0) && (a[j] %2 ==1)){
                log.info("i:{} j:{} change",i,j);
                swap(a, i, j);
                j++;
                i++;
                log.info("i:{} j:{} after change",i,j);
            }else if((a[i] % 2 ==0) && (a[j] %2 ==0)){
                log.info("i:{} j:{} 都是偶数 not change",i,j);
                j++;
                log.info("i:{} j:{} 都是偶数 after not change",i,j);
            }else if((a[i] % 2 ==1) && (a[j] %2 ==0)){
                log.info("i:{} j:{} order not change",i,j);
                i++;
                j++;
                log.info("i:{} j:{} order not change",i,j);
            }
        }
    }

    private void swap(int[] a , int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    @Test
    public void Test33() {
        int a = 10;
        int b = a&1;
        int c = a&0;
        System.out.println(b);
        System.out.println(c);

    }
    /**
     * 定义双指针before,last，before在前面找第一个偶数，last在后面找第一个奇数，找到以后两者进行交换。当before大于last的时候，循环结束。
     */
    private void sortoddWith2Pointers(int[] a){
        int pointEvenLeft = 0;
        int pointOddRight = a.length-1;
        while(pointEvenLeft < pointOddRight){
            if(a[pointEvenLeft] % 2 ==0 && a[pointOddRight] %2 ==1 ){
                swap(a, pointEvenLeft, pointOddRight);
                pointEvenLeft++;
                pointOddRight--;
            }else if(a[pointEvenLeft] % 2 ==0 && a[pointOddRight] %2 ==0){
                pointOddRight --;
            }else if(a[pointEvenLeft] % 2 ==1 && a[pointOddRight] %2 ==0){
                pointEvenLeft++;
                pointOddRight--;
            }else if(a[pointEvenLeft] % 2 ==1 && a[pointOddRight] %2 ==1){
                pointEvenLeft++;
            }
        }
    }

    /**
     * 比较优雅的写法，但是还有更好的判断奇偶的方式
     * @param nums
     */
    public void exchange(int[] nums) {
        int before = 0;
        int last = nums.length - 1;
        while (before < last) {
            while (before < last && nums[before] % 2 == 1) {
                before++;
            }
            while (before < last &&  nums[last] % 2 == 0) {
                last--;
            }
            int temp = nums[before];
            nums[before] = nums[last];
            nums[last] = temp;
        }
        return;
    }

    @Test
    public void Test111() {
        int a =-103;
        int b = a & 1;
        int c = a | 1;
        System.out.println(b);
        System.out.println(c);

    }
}
