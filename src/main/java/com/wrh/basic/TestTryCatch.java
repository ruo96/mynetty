package com.wrh.basic;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:34 2019/6/6 0006
 * @Modified By:
 */
public class TestTryCatch {
    public static void main(String[] args) {
        int i = tryCatch();
        System.out.println(i);
    }

    private static int tryCatch() {

        try{
            System.out.println("return 1");
            throw  new Exception();
//            return 1;
        }catch (Exception e){
            System.out.println("return 2");
            return 2;
        }finally {
            System.out.println("return 3");
            return 3;
        }
    }
}
