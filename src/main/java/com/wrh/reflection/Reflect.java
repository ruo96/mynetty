package com.wrh.reflection;

public class Reflect {

    private String name;
    private int age;

    public Reflect(int age) {
        this.age = age;
    }

    private void speak(String name) {
        System.out.println("My name is" + name);
    }

    public Reflect(String name) {
        this.name = name;
    }
}
