package com.wrh.algorithum.cal.code20191121;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:43 2019/11/21 0021
 * @Modified By:
 */
@Slf4j
public class TestArrays {

    @Test
    public void test1(){
        int[] a = new int[]{4,2,3,7,8,9};
        int[] b = new int[]{6,1,5,9,10,13};

        int[] c = new int[a.length];

        for (int i = 0, length = a.length; i < length; i++) {
            c[i] = b[i] - a[i];
        }
        log.info("===> c: {}", JSON.toJSONString(c));

        int i = 0;
        for (int j = 0 ,length = c.length; j < length; j++) {
            i += c[j];
        }
        log.info("===> 总差值为：{}", i);
        boolean flag = false;

        //b比a总量大11,a需要把小的换成b的大的数值， 此处不做if判断了，暂时先用a来当基数，根据差值可以选择不同方法，此处略掉
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0 ,length = a.length; j < length; j++) {
            int k = a[j];
            for (int l = 0,length1 = b.length ; l < length1; l++) {
                int m = b[l];
                int diff = m - k;
                if(diff > 0){
                    if( i/2 == diff ){
                        //找到了
                        int temp = a[j];
                        a[j] = b[l];
                        b[l] = temp;
                        flag  = true;
                        break;
                    }
                }
            }
            if(flag){
                break;
            }
        }
        log.info("===> a: {}", JSON.toJSONString(a));
        log.info("===> b: {}", JSON.toJSONString(b));

        log.info("===>{}",calDiff(a,b));

    }

    private int calDiff(int[] a, int[] b) {
        int m = 0;
        int n = 0;
        for (int i = 0; i < a.length; i++) {
            m += a[i];
            n += b[i];
        }

        return m-n;


    }

    @Test
    public void Test81() {
        Integer[] data = {1,2,3,4};
        System.out.println(Arrays.toString(data));

        /**
         * 填充全部
         */
        Arrays.fill(data,9);
        System.out.println(Arrays.toString(data));

        /**
         * 填充指定位置
         */
        Arrays.fill(data, 0,2,10);
        System.out.println(Arrays.toString(data));
    }

    /**
     * 数组降序排列
     */
    @Test
    public void Test100() {
        Integer[] data = {1,2,3,4,0,7,3};
        Arrays.sort(data);

        System.out.println(Arrays.toString(data));
        Arrays.sort(data, Collections.reverseOrder());
        System.out.println(Arrays.toString(data));

    }

    /**
     * 数组降序排列
     */
    @Test
    public void Test109() {
        Integer[] data = {1,2,3,4,0,7,3};
        Comparator cmp = new CMP();
        Arrays.sort(data, cmp);
        System.out.println(Arrays.toString(data));

    }

    class CMP implements Comparator<Integer>{

        @Override
        public int compare(Integer a, Integer b) {
            return b - a;
        }
    }
}
