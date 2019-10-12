package com.wrh.collection.list;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:12 2019/8/14 0014
 * @Modified By:
 */
@Slf4j
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

    /**
     * java List转换为字符串并加入分隔符的一些方法总结
     */
    @Test
    public void test(){

    }

    /**
     *
     * @param list
     * @param separator
     * @return
     */
    public String listToString1(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public String listToString2(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i));
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public String listToString3(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }



    public String listToString(List list, char separator) {
        return org.apache.commons.lang.StringUtils.join(list.toArray(), separator);
    }



}
