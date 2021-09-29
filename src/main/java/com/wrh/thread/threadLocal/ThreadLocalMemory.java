package com.wrh.thread.threadLocal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuruohong
 * @Classname ThreadLocalMemory
 * @Description TODO
 * @Date 2021/9/26 17:30
 */
public class ThreadLocalMemory {
    // Thread local variable containing each thread's ID
    public ThreadLocal<List<Object>> threadId = new ThreadLocal<List<Object>>() {
        @Override
        protected List<Object> initialValue() {
            List<Object> list = new ArrayList<Object>();
            for (int i = 0; i < 10000; i++) {
                list.add(String.valueOf(i));
            }
            return list;
        }
    };
    // Returns the current thread's unique ID, assigning it if necessary
    public List<Object> get() {
        return threadId.get();
    }
    // remove currentid
    public void remove() {
        threadId.remove();
    }
}
