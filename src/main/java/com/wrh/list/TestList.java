package com.wrh.list;

import com.wrh.utils.GsonUtils;
import com.wrh.utils.test.Dog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:05 2019/1/9 0009
 * @Modified By:
 */
@Slf4j
public class TestList {
    public static void main(String[] args) {
        ArrayList<String> listOne = new ArrayList<String>();
        listOne.add("this is list");

        Vector<String> vector = new Vector<String>();
        vector.add("this is list");
        System.out.println(listOne.equals(vector));

        handleList(listOne);
        System.out.println("handle: " + listOne);
    }

    private static void handleList(List list){
        list.add("123");
    }

    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        Vector<String> vector = new Vector<>();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("3");

        System.out.println(list.equals(vector));
    }

    @Test
    public void Test() {
        Dog d1 = new Dog();
        d1.setColor("1");
        d1.setName("2");
        d1.setAge(3);
        d1.setOwner("4");

        List<Dog> list = new ArrayList<>();
        list.add(d1);

        Dog d2 = new Dog();
        BeanUtils.copyProperties(d1,d2);
        d2.setColor("5");

        list.add(d2);
        log.info(">>> Gson: {}", GsonUtils.GSON.toJson(list));
        log.info(">>> Long: {}", Long.valueOf("0"));



    }
}