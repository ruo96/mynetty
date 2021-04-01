package com.wrh.algorithum.findleftright;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestLeftRight
 * @Description TODO
 * @Date 2021/4/1 16:54
 */
@Slf4j
public class TestLeftRight {

    @Test
    public void Test14() {
        int[] nums = new int[]{1,1,2,2,2,2,3,3,4,5};
        System.out.println("left(nums,2) = " + left(nums, 2));
        System.out.println("right(nums, 2) = " + right(nums, 2));

    }

    private int left(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left <= right) {
            System.out.println("while  left:" + left + "   right:" + right);
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                //缩小右边界，在左半部分继续查找
                System.out.println(" == change mid :" + mid);
                right = mid - 1;
                System.out.println(" == change right :" + right);
            } else if (target < nums[mid]) {
                System.out.println(" < change mid :" + mid);
                right = mid - 1;
                System.out.println(" < change right :" + right);
            } else if (target > nums[mid]) {
                System.out.println(" > change mid :" + mid);
                left = mid + 1;
                System.out.println(" > change left :" + left);
            }
        }

        if (left == nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    private int right(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left <= right) {
            System.out.println("while  left:" + left + "   right:" + right);
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                //缩小右边界，在左半部分继续查找
                System.out.println(" == change mid :" + mid);
                left = mid + 1;
                System.out.println(" == change left :" + left);
            } else if (target < nums[mid]) {
                System.out.println(" < change mid :" + mid);
                right = mid - 1;
                System.out.println(" < change right :" + right);
            } else if (target > nums[mid]) {
                System.out.println(" > change mid :" + mid);
                left = mid + 1;
                System.out.println(" > change left :" + left);
            }
        }

        if (right == -1 || nums[right] != target) {
            return -1;
        }
        return right;
    }
}
