package com.wrh.basic;

import org.junit.Test;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2023/2/20 10:36
 */
public class Hello {
    public static void main(String[] args) {
        System.out.println("hello world!");
    }
    
    @Test
    public void Test14() {
       String code = "import numpy as np\n" +
               "dt = np.dtype([('age',np.int8)]) \n" +
               "a = np.array([(10,),(20,),(30,)], dtype = dt) \n" +
               "print(a['age'])";

        System.out.println("code = " + code);
    }
}
