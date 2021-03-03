package com.wrh.designMode.strategyMode.example.strategyMode;

/**
 * @author wuruohong
 * @Classname ConcreteStrategy
 * @Description TODO
 * @Date 2021/1/28 20:10
 */
public class ConcreteStrategy implements IStrategy{

    //具体的算法实现
    @Override
    public void algorithmMethod() {
        System.out.println("this is ConcreteStrategy method...");
    }
}
