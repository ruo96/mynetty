package com.wrh.collection.stack;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:28 2019/11/29 0029
 * @Modified By:
 */
@Slf4j
public class TestStack {
    @Test
    public void test1(){

    }

    class MyStack {
        private int[] storage;

        private int capacity;

        private int count;

        private static final int GROW_FACTOR = 2;

        public MyStack() {
            this.capacity = 8;
            this.storage = new int[8];
            this.count = 0;
        }

        public MyStack(int initialCapacity) {
            if(initialCapacity < 1) {
                throw new IllegalArgumentException("capacity too small");
            }
            this.capacity = initialCapacity;
            this.storage = new int[initialCapacity];
            this.count = 0;
        }

        public void push(int value) {
            if( count == capacity) {
                ensureCapacity();
            }
            storage[count ++] = value;
        }

        private void ensureCapacity() {
            int newCapacity = capacity * GROW_FACTOR;
            storage = Arrays.copyOf(storage, newCapacity);
            capacity = newCapacity;
        }

        private int pop() {
            count --;
            if( count == -1) {
                throw new IllegalArgumentException("stack is empty");
            }
            return storage[count];
        }

        private int peek() {
            if(count == 0) {
                throw new IllegalArgumentException("stack is empty");
            }else {
                return storage[count - 1];
            }
        }

        private boolean isEmpty() {
            return count == 0;
        }

        private int size() {
            return count;
        }
    }

    @Test
    public void test2(){
        int[] nums = {1,2,3,1};
        int a = removeDuplicates(nums);
        log.info("===> a : {}",a);
        log.info("===> nums : {}", JSON.toJSONString(nums));
    }

    public int removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int length = nums.length;
        List<Integer> resultList = new ArrayList<>(nums.length);
        for(int i : nums){
            resultList.add(i);
        }

        Iterator<Integer> integerIterator = resultList.iterator();
        while (integerIterator.hasNext()){
            int data = integerIterator.next();
            if(set.contains(data)){
                length --;
                integerIterator.remove();
            }else {
                set.add(data);
            }
        }
        int[] numsNew = resultList.stream().mapToInt(Integer::valueOf).toArray();

        nums = numsNew;

        log.info("===> numsNew : {}", JSON.toJSONString(numsNew));
        log.info("===> nums : {}", JSON.toJSONString(nums));
        System.arraycopy(numsNew, 0, nums, 0, set.size());

        return set.size();
    }
}
