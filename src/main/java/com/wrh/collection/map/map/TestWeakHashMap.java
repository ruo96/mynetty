package com.wrh.collection.map.map;

import com.alibaba.fastjson.JSON;
import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @author wuruohong
 * @Classname TestWeakHashMap
 * @Description TODO
 * @Date 2021/1/11 19:40
 */
@Slf4j
public class TestWeakHashMap {
    @Test
    public void Test14() {
        WeakHashMap<String, List<WeakReference<Student>>> map = new WeakHashMap<>();
        List<WeakReference<Student>> list = new ArrayList<>();
        Student s = new Student();
        s.setName("ww");
        s.setId(0);
        s.setGrade(0);
        s.setMoney(0L);
        s.setTitle("");
        s.setFlag(false);
        WeakReference<Student> w = new WeakReference<Student>(s);
        s = null;
        list.add(w);
        map.put("w1",list);
        System.out.println(JSON.toJSONString(map));
        System.gc();
        System.out.println(JSON.toJSONString(map));

    }
}
