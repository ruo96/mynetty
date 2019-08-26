package com.wrh.collection.list;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:12 2019/8/14 0014
 * @Modified By:
 */
public class TestJoin {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");

        for (String s : list) {

        }

        for (int i = 0; i < list.size(); i++) {

        }

        boolean trueFlag = true;

        System.out.println(new Date().getTime());
        System.out.println(System.currentTimeMillis());
        System.out.println(DateTime.now().getMillis());

        System.out.println(StringUtils.join(list,"-"));
    }
}
