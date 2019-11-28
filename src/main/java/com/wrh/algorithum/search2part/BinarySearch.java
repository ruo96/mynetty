package com.wrh.algorithum.search2part;

import lombok.extern.slf4j.Slf4j;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:18 2019/11/25 0025
 * @Modified By:
 */
@Slf4j
public class BinarySearch {

    private String name;

    public static int search(int[] a, int b){
        int low = 0;
        int high = a.length - 1;
        int middle ;

        while(low <= high){
            middle = (low+high)/2;
            if(a[middle] == b){
                log.info("===> 找到了，位置在{}",middle);
                return middle;
            }else if(b > a[middle]){
                low = middle + 1;
            }else {
                high = middle - 1;
            }
        }
        return -1;
    }
}
