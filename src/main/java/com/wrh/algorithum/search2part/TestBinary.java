package com.wrh.algorithum.search2part;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestBinary
 * @Description TODO
 * @Date 2020/6/2 15:00
 */
@Slf4j
public class TestBinary {
    @Test
    public void Test1() {

        int[] a = {1,2,3,4,5};

        int b = 4;

        int[] c = insertInto(a, b);

    }

    private int[] insertInto(int[] a, int b) {
        int begin = 0;
        int end = a.length - 1;
        int[] newA = new int[a.length + 1];

        if (b <= a[begin]) {
            newA[0] = b;
            for (int i = 0; i < a.length; i++) {
                newA[i+1] = a[i];
            }
            return newA;
        } else if( b > a[end] ){
            for (int i = 0; i < a.length; i++) {
                newA[i] = a[i];
            }
            newA[a.length] = b;
            return newA;
        } else {
            return insertNow(a, b, 0, a.length-1);
        }
    }

    private int[] insertNow(int[] a, int b, int begin, int end) {
        int mid = end/2;
        if(b > a[end]) {

        }

        return null;

    }
}
