package com.wrh.algorithum.stack;

import java.util.Stack;

/**
 * @author wuruohong
 * @Classname MinStack
 * @Description TODO
 * @Date 2020/12/29 11:39
 */
public class MinSingleStack {
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<>();

    public void push(int x) {
        //如果加入的值小于最小值，要更新最小值
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        //如果把最小值出栈了，就更新最小值
        if (stack.pop() == min)
            min = stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min;
    }
}
