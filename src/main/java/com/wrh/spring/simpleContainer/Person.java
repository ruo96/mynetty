package com.wrh.spring.simpleContainer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wuruohong
 * @Classname Person
 * @Description TODO
 * @Date 2021/12/15 14:41
 */
@Component
@Data
public class Person {
    static {
        System.out.println("this is person Static");
    }

    @Value("mp")
    String name;

    @Value("10")
    int age;

    String addr;



    public Person(String name, int age,String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
        System.out.println("constuctor ...");
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("person.setName。。。。"+name);
    }

    public void setAge(int age) {
        this.age = age;
        System.out.println("person.setAge: "+ age);
    }

    public void setAddr(String addr) {
        this.addr = addr;
        System.out.println("person.setAddr: "+addr);
    }

    public Person() {
        System.out.println("constructor null");
    }

    public void p1(){
        System.out.println("this name:"+this.name+"  this age:"+this.age);
    }


    /*@Bean("p1")
    public Person getPerson(){
        System.out.println("自定义...");
        return new Person("zhuge",100,"wuhan");
    }*/
}
