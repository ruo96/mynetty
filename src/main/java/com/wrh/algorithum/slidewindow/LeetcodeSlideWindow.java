package com.wrh.algorithum.slidewindow;

import org.junit.Test;

import java.util.*;

/**
 * @author wuruohong
 * @Classname LeetcodeSlideWindow
 * @Description TODO
 * @Date 2021/11/16 14:19
 */
public class LeetcodeSlideWindow {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    @Test
    public void Test12() {
        int len = lengthOfLongestSubstringV2("abcdeafgb");
        System.out.println("len = " + len);

    }

    int lengthOfLongestSubstring(String s){

        List<String> list = new ArrayList<>();

        int i = 0;
        int length = s.length();

        int maxLength = 1;
        StringBuffer sb = new StringBuffer();
        sb.append(s.charAt(0));
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));

        for (i = 0; i < length-1; i++) {
            /** 定义开始位置*/
            sb = new StringBuffer();
            sb.append(s.charAt(i));
            set.add(s.charAt(i));

            for (int j = i+1; j < length; j++) {
                char a = s.charAt(j);
                if (!set.contains(a)) {
                    sb.append(a);
                    set.add(a);
                } else {
                    set.clear();
                    i++;
                    list.add(sb.toString());
                    break;
                }
                maxLength++;
            }
            list.add(sb.toString());
        }
        System.out.println("list = " + list);
        String result = list.stream().max(Comparator.comparing(String::length)).get();
        System.out.println("result = " + result);
        return result.length();
    }

    int lengthOfLongestSubstringV2(String s){
        //获取原字符串的长度
        int len = s.length();
        //维护一个哈希集合的窗口
        Set<Character> windows = new HashSet<>();
        int left=0,right =0;
        int res =0;

        List<String> list = new ArrayList<>();

        while(right<len){
            char c = s.charAt(right);
            //窗口右移
            right++;

            //判断是否左边窗口需要缩减，如果已经包含，那就需要缩减
            while(windows.contains(c)){
                windows.remove(s.charAt(left));
                left++;
            }
            windows.add(c);
            //比较更新答案
            res = Math.max(res,windows.size());
        }
        return res;
    }

    /**
     * 给你一个字符串S、一个字符串T。返回S中涵盖T所有字符的最小子串。如果S中不存在涵盖T所有字符的子串，则返回空字符串 ""
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     *
     * 输入：s = "a", t = "a"
     * 输出："a"
     */
    @Test
    public void Test96() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println("minWindow(s, t) = " + minWindowV2(s, t));
    }

    public String minWindowV2(String s, String t) {
        Map<Character, List<Integer>> map = new HashMap<>();
        char[] c = t.toCharArray();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < c.length; i++) {
            list = new ArrayList<>();
            for (int j = 0; j < s.length(); ) {
                int index = s.indexOf(c[i],j);
                if (index >= 0) {
                    list.add(index);
                    j = index + 1;
                } else {
                    break;
                }
            }
            map.put(t.charAt(i), list);
            System.out.println("map = " + map);
        }
        return "";
    }

    public String minWindow(String s, String t) {
        if (s.length() == 0 || s.length() < t.length()) {
            return "";
        }

        int sLen = s.length();
        Map<Character, Integer> lookup = new HashMap<>();

        for (int i = 0; i < sLen; i++) {
            lookup.put(s.charAt(i), 0);
        }

        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            if (lookup.containsKey(c)) {
                lookup.put(c, lookup.get(c) + 1);
            } else {
                return "";
            }
        }

        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        int tCount = t.length();
        String result = "";
        while (right < sLen) {
            char c = s.charAt(right);
            if (lookup.get(c) > 0) tCount--;
            lookup.put(c, lookup.get(c) - 1);
            //窗口右移
            right++;

            //已经包含T的所有字母
            while (tCount == 0) {
                //比较更新答案
                if (minLen > right - left) {
                    minLen = right - left;
                    result = s.substring(left, right);
                }
                char c2 = s.charAt(left);
                if (lookup.get(c2) == 0) tCount++;
                lookup.put(c2, lookup.get(c2) + 1);
                //窗口从左边缩减
                left++;
            }
        }
        return result;
    }
}
