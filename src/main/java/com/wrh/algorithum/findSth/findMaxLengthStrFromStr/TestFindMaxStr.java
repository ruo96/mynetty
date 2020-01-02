package com.wrh.algorithum.findSth.findMaxLengthStrFromStr;

import com.alibaba.fastjson.JSON;
import com.google.gson.annotations.JsonAdapter;
import org.junit.Test;

public class TestFindMaxStr {
    public static class method1 {
        public static int lengthOfLongestSubString(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int[] tmp = new int[256];
            int maxLen = 0;
            int l = 0;
            int r = 0;
            while(l < s.length()) {
                if ( r < s.length() && tmp[s.charAt(r)] == 0) {
                    tmp[s.charAt(r++)] = 1;
                } else {
                    maxLen = maxLen > (r-1) ? maxLen: (r-1);
                    tmp[s.charAt(l++)] = 0;
                }
            }
            return maxLen;
        }
    }

    public static void main(String[] args) {
//todo 这个算法不对！！！！！！！！！！！！ todo
        int i = TestFindMaxStr.method1.lengthOfLongestSubString("pwwkew");
        System.out.println(i);
    }

    @Test
    public void test$1() {
        String i = "Z1234";
        System.out.println(i.charAt(0));
        System.out.println(i.charAt(1));
        System.out.println(i.charAt(2));
        int[] a = new int[256];
        a[i.charAt(0)] = 1;
        a[i.charAt(1)] = 2;
        a[i.charAt(2)] = 3;
        System.out.println(JSON.toJSONString(a));

        int b = i.charAt(0);
        System.out.println("b is : " + b);

    }
}
