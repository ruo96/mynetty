package com.wrh.list.vo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wrh.collection.list.thread.Car;
import com.wrh.collection.list.thread.Person;
import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:15 2019/9/20 0020
 * @Modified By:
 */
@Slf4j
public class TestJsonParse {

    @Test
    public void test(){

        Integer i1 = 1;
        Integer i2 = 2;
        Map<Integer , Integer> map = new HashMap<>();
        map.put(i1,i2);

        List<Person> list = new ArrayList<>();
        Person p1 = new Person();
        p1.setName("zhang san");
        List<Car> cars = new ArrayList<>();
        Car car1 = new Car();
        car1.setName("benzi");
        car1.setPrice(1);

        cars.add(car1);


        Car car2 = new Car();
        car2.setName("baoma");
        car2.setPrice(map.get(i1));

        cars.add(car2);




        p1.setCars(cars);

        list.add(p1);

        log.info("===>{}", JSON.toJSONString(list));
    }

    @Test
    public void Test60() {
        /*List<Student> s = TestList.getStudentList();
        System.out.println("s = " + s);
        String str = JSON.toJSONString(s);
        List<Student> i = JSONObject.parseArray(str, Student.class);
        System.out.println("i = " + i);*/
        //List<Student> j = JSONObject.parseArray(str, Student.class, Feature.OrderedField);
        //System.out.println("j = " + j);
        Student s1 = new Student();
        s1.setName("ww");
        s1.setId(1);
        s1.setGrade(2);
        s1.setMoney(3L);
        s1.setTitle("title");
        s1.setFlag(true);

        System.out.println("s1 = " + s1);
        String str = JSON.toJSONString(s1);
        Student s2 = JSONObject.parseObject(str, Student.class);
        System.out.println("s2 = " + s2);


    }
}
