package com.wrh.basic.InitailizationProcess;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:58 2019/10/15 0015
 * @Modified By:
 */
public class Derive extends Base {

    private Member m1 = new Member("Member 1");

    {
        System.out.println("Initial Block()");
    }

    public Derive(){
        System.out.println("Derive()");
    }

    private Member m2 = new Member("Member 2");

    private int i = getInt();

    private int getInt(){
        System.out.println("getInt()");
        return 2;
    }

    /**
     * Base()
     * Member()Member 1
     * Initial Block()
     * Member()Member 2
     * getInt()
     * Derive()
     *
     * @param args
     */
    public static void main(String[] args) {
        new Derive();
    }
}
