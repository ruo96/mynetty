package com.wrh.annotate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:28 2019/10/16 0016
 * @Modified By:
 */
@Slf4j
public class TestSuppressWarnings {

    public static void main(String[] args) {
        System.out.println(findResult());
    }


//    @SuppressWarnings("finally")
    public static Integer findResult(){
        Integer aInteger = 5;

        try{
            return aInteger = 6;
        }catch (Exception e){
            return aInteger = 7;
        }finally {
            return aInteger = 8;
        }
    }

    @Test
    public void test(){
        System.out.println(findResult());
    }
}
