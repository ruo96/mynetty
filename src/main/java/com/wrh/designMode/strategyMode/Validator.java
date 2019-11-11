package com.wrh.designMode.strategyMode;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:05 2019/10/15 0015
 * @Modified By:
 */
public class Validator {
    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 给用户的接口
     * @param s
     * @return
     */
    public boolean validate(String s){
        return strategy.execute(s);
    }
}
