package com.wrh.algorithum;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author wuruohong
 * @Classname MaxNumInArray
 * @Description TODO
 * @Date 2020/12/8 18:32
 */
@Slf4j
public class MaxNumInArray {

    @Test
    public void Test15() {
        int[] array = {1,2,3,2,2,2,5,4,1};
        System.out.println(majorityElement(array));
        System.out.println(majorityElement1(array));

    }

    public int majorityElement(int[] nums) {
        int count = 1;
        int candidate = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (candidate == nums[i]){
                count++;
            }else {
                count--;
                if (count == 0) {  //若count为0，更换候选人
                    candidate = nums[i];
                    count++;
                }
            }
        }
        return candidate;
    }

    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

}
