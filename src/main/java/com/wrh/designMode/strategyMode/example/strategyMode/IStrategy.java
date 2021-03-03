package com.wrh.designMode.strategyMode.example.strategyMode;

/**
 * @author wuruohong
 * @Classname IStrategy
 * @Description 策略接口
 * @Date 2021/1/28 20:10
 */
public interface IStrategy {
    //定义的抽象算法方法 来约束具体的算法实现方法
    public void algorithmMethod();
}
