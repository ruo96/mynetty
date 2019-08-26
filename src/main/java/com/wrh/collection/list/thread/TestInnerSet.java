package com.wrh.collection.list.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:46 2019/5/16 0016
 * @Modified By:
 */
public class TestInnerSet {
    public static void main(String[] args) {
        Person person = new Person();
        Car car = new Car();
        car.setName("benz");
        car.setPrice(1000000);

        List<Car> carList = new ArrayList<>();
        carList.add(car);
        person.setCars(carList);
        person.setName("wrh");
        System.out.println("before: {}"+person);

        Car car1 = new Car();
        car1.setName("dazhong");
        car1.setPrice(100000);

        person.getCars().add(car1);
        System.out.println("after: {}"+person);

        person.getCars().get(0).setName("bujiadi");
        System.out.println("after: {}"+person);


    }
}
