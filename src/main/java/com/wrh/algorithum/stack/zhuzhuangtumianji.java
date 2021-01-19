package com.wrh.algorithum.stack;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author wuruohong
 * @Classname zhuzhuangtumianji
 * @Description TODO
 * @Date 2021/1/4 14:43
 */
public class zhuzhuangtumianji {

    @Test
    public void Test12() {
        System.out.println(largestRectangleArea(new int[]{1,2,3,4}));

    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if(n == 0){
            return 0;
        }
        int[] left = new int[n];
        int[] right = new int[n];

        //第一根柱子，左边不存在比它小的
        left[0] = - 1;
        //最后一根柱子，右边不存在比它小的
        right[n - 1] = n;

        for(int i = 1; i < n; i++){
            int temp = i - 1;
            while(temp >= 0 && heights[temp] >= heights[i]){
                temp = left[temp];
            }
            //当上述循环 break 后，  temp 即为左边第一根小于 i 位置的柱子
            left[i] = temp;
        }

        for(int i = n - 2; i >= 0; i--){
            int temp = i + 1;
            while(temp < n && heights[temp] >= heights[i]){
                temp = right[temp];
            }
            //当上述循环 break 后，  temp 即为左边第一根小于 i 位置的柱子
            right[i] = temp;
        }

        int maxArea = 0;
        for(int i = 0; i < n; i++){
            int width = right[i] - left[i] - 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }
        return maxArea;
    }

    public int largestRectangleArea1(int[] heights) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] new_heights = new int[heights.length + 2];
        for (int i = 1; i < heights.length + 1; i++) {
            new_heights[i] = heights[i - 1];
        }
        for (int i = 0; i < new_heights.length; i++) {
            while (!stack.isEmpty() && new_heights[stack.peek()] > new_heights[i]) {
                int cur = stack.pop();
                int l = stack.peek();
                int r = i;
                res = Math.max(res, (r - l - 1) * new_heights[cur]);
            }
            stack.push(i);
        }
        return res;
    }
}
