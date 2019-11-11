package com.wrh.collection.list;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Stack;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 11:29 2019/10/15 0015
 * @Modified By:
 */
@Slf4j
public class TestStack {

    /**
     * 先进后出
     */
    @Test
    public void test(){
        Stack<String> stack = new Stack<>();
        stack.push("w1");
        stack.push("w2");
        stack.push("w3");
        log.info("{}",stack.peek());
        stack.pop();
        log.info("{}",stack.peek());
    }
}
