package com.wrh.aop.jdk;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:31 2019/12/11 0011
 * @Modified By:
 */
public class SMSServiceImpl implements SMSService {
    @Override
    public void sendMessage() {
        System.out.println("您正在执行充值操作");
    }
}
