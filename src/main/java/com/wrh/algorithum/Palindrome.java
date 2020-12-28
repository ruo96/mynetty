package com.wrh.algorithum;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wuruohong
 * @Classname Palindrome
 * @Description TODO
 * @Date 2020/12/25 15:52
 */
public class Palindrome {
    public boolean isPalindrome(String s) {
        String filteredS = filterNonNumberAndChar(s);

        String reverseS = reverseString(s);

        return reverseS.equalsIgnoreCase(filteredS);
    }

    private String reverseString(String s){
        return new StringBuilder(s).reverse().toString();
    }

    private String filterNonNumberAndChar(String s){
        return s.replaceAll("[^A-Za-z0-9]","");
    }

    @Test
    public void Test27() {
        System.out.println(isPalindrome("carrac1"));

    }

    private void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                if (i != j) {
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

    @Test
    public void Test48() {
        int[] i = {1,0,0,2};
        System.out.println(Arrays.toString(i));
        moveZeroes(i);
        System.out.println(Arrays.toString(i));

    }
}
