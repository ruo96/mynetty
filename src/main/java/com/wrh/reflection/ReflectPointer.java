package com.wrh.reflection;

/**
 * @author wuruohong
 * @Classname ReflectPointer
 * @Description TODO
 * @Date 2021/4/23 11:50
 */
public class ReflectPointer {
    private int x = 0;
    public int y = 0;
    public String str1 = "ball";
    public String str2 = "basketball";
    public String str3 = "itcat";

    public ReflectPointer(int x,int y) {//alt + shift +s相当于右键source
        super();
        // TODO Auto-generated constructor stub
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "ReflectPointer [str1=" + str1 + ", str2=" + str2 + ", str3="
                + str3 + "]";
    }
}
