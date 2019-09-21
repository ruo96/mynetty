package com.wrh.forEachException;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:46 2019/9/18 0018
 * @Modified By:
 */
@Slf4j
public class TestForEachException {

    @Test
    public void test(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        /*list.forEach(e->{
            if(e == 3){
                try {
                    throw new Exception();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }else {
                log.info("list is : {}",e);
            }
        });*/

        for (Integer i: list ) {
            if(i == 3){
                try {
                    throw new Exception();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }else {
                log.info("list is : {}",i);
            }
        }

    }
}
