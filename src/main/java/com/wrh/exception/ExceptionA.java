package com.wrh.exception;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:11 2019/12/11 0011
 * @Modified By:
 */
public class ExceptionA extends RuntimeException {
    public ExceptionA() {
        super();
    }

    public ExceptionA(String message) {
        super(message);
    }
}
