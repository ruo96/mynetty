package com.wrh.algorithum.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Stack;

/**
 * @author wuruohong
 * @Classname TestStack
 * @Description TODO
 * @Date 2020/12/30 10:54
 */
@Slf4j
public class TestStack {
    @Test
    public void Test14() {
        Stack<String> stack = new Stack<>();
        stack.add("w1");
        stack.add("w2");
        stack.add("w3");
        stack.add("w4");
        System.out.println(stack);
        System.out.println(stack.search("w4"));
        System.out.println(stack.search("w3"));
        System.out.println(stack.search("w2"));
        System.out.println(stack.search("w1"));
        System.out.println(stack.size());

    }
}
