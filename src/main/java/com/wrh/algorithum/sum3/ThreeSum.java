package com.wrh.algorithum.sum3;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.security.util.Length;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

    /**
     *  比较快的写法
     * @param nums
     * @return
     */
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

    /**
     * 判断是否存在3个数字 a+b+c = target  双指针   夹逼
     */
    @Test
    public void Test77() {
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,-4};
        System.out.println(threesumV2(nums));

    }

    public static List<List<Integer>> threesumV2(int[] num){
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length-2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i-1])) {
                int lo = i+1, hi = num.length-1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo+1]) lo++;
                        while (lo < hi && num[hi] == num[hi-1]) hi--;
                        lo++; hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }

    @Test
    public void Test109() {
        int[] a = {-1,0,1,2,4,-3,-2,-1};
        System.out.println(threeSum1(a));

    }

    /**
     * 巧妙的利用双指针夹逼的写法
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int k = 0; k < nums.length - 2; k++){
            if(nums[k] > 0) break;
            if(k > 0 && nums[k] == nums[k - 1]) continue;
            int i = k + 1, j = nums.length - 1;
            while(i < j){
                int sum = nums[k] + nums[i] + nums[j];
                if(sum < 0){
                    while(i < j && nums[i] == nums[++i]);
                } else if (sum > 0) {
                    while(i < j && nums[j] == nums[--j]);
                } else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while(i < j && nums[i] == nums[++i]);
                    while(i < j && nums[j] == nums[--j]);
                }
            }
        }
        return res;
    }

    @Test
    public void Test144() {
        int[] a = {-1,0,1,2,-1,-4};
        System.out.println(mySum3(a));

    }

    /**
     * 1次 2次 3次 4次  5次
     * @param sum
     * @return
     */
    private List<List<Integer>> mySum3(int[] sum) {
        Arrays.sort(sum);
        List<List<Integer>> list = new ArrayList<>();

        int s;
        int len = sum.length;
        for (int k = 0; k < len-2; k++) {
            if (sum[k] > 0) {
                break;
            }
            if (k > 0 && sum[k] == sum[k - 1]) {
                continue;
            }
            int i =k+1;
            int j = len - 1;
            while (i < j) {
                s = sum[k] + sum[i] + sum[j];
                if (s < 0) {
                    while (i < j && sum[i] == sum[++i]) {

                    }
                } else if (s > 0) {
                    while (i < j && sum[j] == sum[--j]) {

                    }
                } else {
                    list.add(new ArrayList<>(Arrays.asList(sum[k],sum[i],sum[j])));
                    while (i < j && sum[i] == sum[++i]) {

                    }
                    while (i < j && sum[j] == sum[--j]) {

                    }
                }
            }
        }
        return list;
    }

}
