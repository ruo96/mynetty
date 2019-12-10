package com.wrh.algorithum.sort.insertsorted;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:27 2019/12/10 0010
 * @Modified By:
 */
@Slf4j
public class TestInsertSorted {

    @Test
    public void test1(){
        int[] a = {2,5,3,8,4,6,1,7};
        insertSort(a);
        log.info("{}", JSON.toJSONString(a));
    }

    /**\
     *
     * 插入排序的思想就是分成已拍好的和为拍好的，然后每次用未排好序的右边的第一个和左边的已排好的一个一个进行对比，如果比对方小，那么对方就后移，如果比对方大，那就break掉，然后在对应位置放下待排序的这个值
     * @param a
     */
    private void insertSort(int[] a) {
        if(a.length < 2) {
            return;
        }

        for (int i = 1; i < a.length; i++) {
            int value = a[i];
            int j;
            for ( j = i-1; j >= 0 ; j--) {
                if(value < a[j]) {
                    a[j+1] = a[j];
                }else {
                    return;
                }
            }
            a[j+1] = value;
        }
    }


}
