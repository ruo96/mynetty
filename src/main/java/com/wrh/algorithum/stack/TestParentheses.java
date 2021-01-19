package com.wrh.algorithum.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author wuruohong
 * @Classname TestParentheses
 * @Description TODO
 * @Date 2020/12/30 11:44
 */
@Slf4j
public class TestParentheses {
    @Test
    public void Test14() {


    }

    /**
     * 我自己的解法
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Map<Integer,Character> leftMap = new HashMap<>();
        leftMap.put(0,'(');
        leftMap.put(1,'{');
        leftMap.put(2,'[');

        Map<Character, Integer> rightMap = new HashMap<>();
        rightMap.put(')', 0);
        rightMap.put('}', 1);
        rightMap.put(']', 2);

        char[] strs = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        /** 如果为右括号，则必须消除*/
        /** 采用消去法*/
        for(char c : strs){
            if (stack.empty()) {
                if (leftMap.containsValue(c)) {
                    stack.add(c);
                    continue;
                } else {
                    return false;
                }
            }
            if (leftMap.containsValue(c)) {
                stack.add(c);
                continue;
            }

            if (rightMap.containsKey(c)) {
                if (stack.peek().equals(leftMap.get(rightMap.get(c)))) {
                    stack.pop();
                    continue;
                }
            }
            return false;
        }
        if (stack.empty()) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void Test45() {
        List<String> list = new ArrayList<>();
        list.add("w1");
        list.add("w2");
        list.add("w3");
        list.add("w4");
        System.out.println(list.indexOf("w5"));

    }

    public boolean isValid2(String s) {
        Map<Character, Integer> leftMap = new HashMap<>();
        leftMap.put('(',0);
        leftMap.put('{',1);
        leftMap.put('[',2);

        Map<Integer, Character > rightMap = new HashMap<>();
        rightMap.put(0,')');
        rightMap.put(1,'}');
        rightMap.put(2,']');


        char[] strs = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        /** 如果为右括号，则必须消除*/
        /** 采用消去法*/
        for(char c : strs){
            if (leftMap.containsKey(c)) {
                stack.add(rightMap.get(leftMap.get(c)));
            }else {
                if (stack.empty()) {
                    if (rightMap.containsValue(c)) {
                        return false;
                    }
                }else {
                    if (stack.peek().equals(c)) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        if (stack.empty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValid3(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();

    }
}
