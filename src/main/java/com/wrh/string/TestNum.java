package com.wrh.string;

import cn.hutool.core.util.NumberUtil;
import com.wrh.elasticsearch.Student;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:50 2019/7/12 0012
 * @Modified By:
 */
public class TestNum {
    public static void main(String[] args) {
        String a = "1.11";
        String b = "1.00";
        System.out.println(isInteger(a));
        System.out.println(isInteger(b));

    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    @Test
    public void Test1() {
        Student s = new Student();

        long b = s.getGrade();
//        System.out.println(b);
    }

    @Test
    public void Test36() {

        int i = NumberUtil.parseInt("1.0");
        System.out.println(i);

    }

    @Test
    public void Test45() {
        Student s = null;
        s.setTitle("w1");
        System.out.println(s);

    }
}
