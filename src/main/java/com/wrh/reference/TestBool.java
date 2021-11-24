package com.wrh.reference;

import org.springframework.stereotype.Component;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:09 2018/10/30 0030
 * @Modified By:
 */
@Component
public class TestBool {

    public static void main(String[] args) {
        System.out.println(findResult());
    }

    @SuppressWarnings("finally")
    private static boolean findResult() {
        try{
            return true;
        }catch (Exception e ){
            return true;
        }finally {
            return false;
        }
    }

    public void show() {

        System.out.println("enter TestBool-> show!");
    }
}
