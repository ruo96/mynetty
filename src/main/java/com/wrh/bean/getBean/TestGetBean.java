package com.wrh.bean.getBean;

import com.wrh.elasticsearch.Student;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestGetBean
 * @Description TODO
 * @Date 2021/10/27 16:58
 */

public class TestGetBean {
    @Test
    public void Test12() {
        Student s = StaticMethodGetBean_3.getBean(Student.class);
        s.getId();

    }
}
