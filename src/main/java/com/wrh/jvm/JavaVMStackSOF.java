package com.wrh.jvm;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:16 2019/11/21 0021
 * @Modified By:
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak(){
        stackLength ++ ;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();

        try {
            oom.stackLeak();
        } catch (Exception e) {
            System.out.println("stack length: " + oom.stackLength);
            throw e;
        }
    }
}
