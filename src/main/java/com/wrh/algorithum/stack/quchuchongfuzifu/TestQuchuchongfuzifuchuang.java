package com.wrh.algorithum.stack.quchuchongfuzifu;

import cn.hutool.core.util.CharUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.jdbc.support.incrementer.PostgresSequenceMaxValueIncrementer;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author wuruohong
 * @Classname TestQuchuchongfuzifuchuang
 * @Description TODO
 * @Date 2021/9/28 17:00
 */
public class TestQuchuchongfuzifuchuang {
    public static void main(String[] args) {
        String original = "abbaca";
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            removeDuplicates(original);
        }
        String result = removeDuplicates(original);
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            removeDuplicatesV2(original);
        }
        String result2 = removeDuplicatesV2(original);
        long start3 = System.currentTimeMillis();
        System.out.println("result = " + result);
        System.out.println("start2 - start1 = " + (start2 - start1));
        System.out.println("start3 - start2 = " + (start3 - start2));
    }

    public static String removeDuplicates(String s) {
        /**
         * 基本思想就是使用栈来判断
         */
        Stack<String> stack = new Stack<>();
        String[] list = s.split("");
//        System.out.println("Arrays.toString(list) = " + Arrays.toString(list));
        for (String a : list) {
            if (stack.isEmpty()) {
                stack.add(a);
            } else {
                if (a.equals(stack.peek())) {
                    stack.pop();
                } else {
                    stack.add(a);
                }
            }
        }
//        System.out.println("stack = " + stack);
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return  sb.reverse().toString();
    }

    public static String removeDuplicatesV2(String s) {
        // 将 res 当做栈
        StringBuffer res = new StringBuffer();
        // top为 res 的长度
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 当 top > 0,即栈中有字符时，当前字符如果和栈中字符相等，弹出栈顶字符，同时 top--
            if (top >= 0 && res.charAt(top) == c) {
                res.deleteCharAt(top);
                top--;
                // 否则，将该字符 入栈，同时top++
            } else {
                res.append(c);
                top++;
            }
        }
        return res.toString();
    }

    public static String removeDuplicatesV3(String s) {
        s = s.toLowerCase();
        // 将 res 当做栈
//        StringBuffer res = new StringBuffer();
        StringBuilder res = new StringBuilder();
        // top为 res 的长度
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 当 top > 0,即栈中有字符时，当前字符如果和栈中字符相等，弹出栈顶字符，同时 top--
            if (top >= 0 && (res.charAt(top) == c)) {
                res.deleteCharAt(top);
                top--;
                // 否则，将该字符 入栈，同时top++
            } else {
                res.append(c);
                top++;
            }
        }
        return res.toString();
    }

    @Test
    public void Test104() {
        String original = "abbAcDdef";
        System.out.println("removeDuplicatesV3(original) = " + removeDuplicatesV3(original));

    }

    @Test
    public void Test113() {
        String original = "abbaca";
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            removeDuplicatesV2(original);
        }
//        String result = removeDuplicates(original);
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            removeDuplicatesV3(original);
        }
//        String result2 = removeDuplicatesV2(original);
        long start3 = System.currentTimeMillis();
//        System.out.println("result = " + result);
        System.out.println("v2 start2 - start1 = " + (start2 - start1));
        System.out.println("v3 start3 - start2 = " + (start3 - start2));
    }
}
