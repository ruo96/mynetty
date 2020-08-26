package com.wrh.algorithum.sum3;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wuruohong
 * @Classname ThreeSum
 * @Description TODO
 * @Date 2020/8/21 14:34
 */
@Slf4j
public class ThreeSum {

    /**
     * 数组nums中包含n个整数，判断其中是否存在三个元素a、b、c， 并且使得a+b+c=0。 找出所有满足条件且不重复的三元组[a, b, c]。
     */
    @Test
    public void Test15() {
        int[] nums = {1,0,-1,2,-2};
        List<List<Integer>> list = threeSum(nums);
        log.info(">>> result: {}", JSON.toJSONString(list));

    }

    private List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> nlist = new ArrayList<>();
        Arrays.sort(nums);
        log.info(">>> sort nums: {}", JSON.toJSONString(nums));
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                /** 排列后发现都是>0的，那么就没有结果*/
                break;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                /** 如果i>0 并且和前面一个值相同，那么就继续往下， 因为要求的是不重复的三元组*/
                continue;
            }

            int j = i + 1;
            int k = nums.length -1 ;
            while (j < k) {
                if (nums[j] + nums[k] < -nums[i]) {
                    j++;
                } else if (nums[j] + nums[k] > -nums[i]) {
                    k--;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    nlist.add(list);
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (j < k && k != nums.length - 1 && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
            }
        }
        return nlist;
    }
}
