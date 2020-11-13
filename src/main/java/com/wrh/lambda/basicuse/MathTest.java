package com.wrh.lambda.basicuse;

import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:35 2019/9/27 0027
 * @Modified By:
 */
@Slf4j
public class MathTest {

    /**
     * 计算和值
     */
    @Test
    public void test(){
        List<Integer> numList = Arrays.asList(1,2,3,4,5);

        int a = numList.stream().filter(e->e>2).mapToInt(Integer::intValue).sum();

        log.info("sum is : {}",a);
    }

    @Test
    public void Test() {
        Student student1 = new Student(){
            {
               setId(1);
               setName("w1");
               setGrade(80);
            }
        };

        Student student2 = new Student(){
            {
                setId(2);
                setName("w1");
                setGrade(100);
            }
        };

        Student student3 = new Student(){
            {
                setId(3);
                setName("w2");
                setGrade(20);
            }
        };

        Student student4 = new Student(){
            {
                setId(4);
                setName("w2");
                setGrade(30);
            }
        };

        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        /** map的骚操作  合并计算  分组计算*/

        Map<String, Integer> map = new HashMap<>();
        list.forEach(e->map.merge(e.getName(),e.getGrade(),Integer::sum));
        System.out.println(map);
    }
}
