package com.wrh.collection.set;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:46 2019/7/13 0013
 * @Modified By:
 */
public class TestSet2 {

    public static void main(String[] args) {
        Set<Students> set = new HashSet<>();
        Students s1 = new Students();
        s1.setName("wrh");
        s1.setId(0);
        set.add(s1);

        Students s2 = new Students();
        s2.setName("wrh");
        s2.setId(0);
        set.add(s2);

        System.out.println(set);

        String[] str = new String[3];
        str[0] = "123";
        str[1] = "456";
        str[2] = "789";
        System.out.println(StringUtils.join(str,'-'));

        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        list.add("789");
        System.out.println(StringUtils.join(list,"|"));


    }
}
