package com.wrh.algorithum.stack;

import java.util.Stack;

/**
 * @author wuruohong
 * @Classname MinStack
 * @Description TODO
 * @Date 2020/12/29 11:39
 */
public class MinStack {
    Stack<Integer> A, B;
    /** initialize your data structure here. */
    public MinStack() {
        A = new Stack<>();
        B = new Stack<>();
    }

    public void push(int x) {
        A.add(x);
        if(B.empty() || B.peek() >= x)
            B.add(x);
    }

    public void pop() {
        if(A.pop().equals(B.peek()))
            B.pop();
    }

    public int top() {
        return A.peek();
    }

    public int min() { // B栈顶维护了一个最小值
        return B.peek();
    }
}
