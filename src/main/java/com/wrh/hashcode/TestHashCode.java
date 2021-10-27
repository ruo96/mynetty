package com.wrh.hashcode;

import com.wrh.elasticsearch.Student;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:27 2018/12/26 0026
 * @Modified By:
 */
public class TestHashCode {
    public static void main(String[] args) {
        String a = "CCKS";
        System.out.println("hashcode is : " + a.hashCode());
    }

    @Test
    public void Test16() {
        Student s = new Student();
        System.out.println("s.hashCode() = " + s.hashCode());
        System.out.println("System.identityHashCode(s) = " + System.identityHashCode(s));
    }
}
