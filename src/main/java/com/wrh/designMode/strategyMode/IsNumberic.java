package com.wrh.designMode.strategyMode;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:04 2019/10/15 0015
 * @Modified By:
 */
public class IsNumberic implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
