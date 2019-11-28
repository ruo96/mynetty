package com.wrh.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:36 2019/11/21 0021
 * @Modified By:
 */
@Slf4j
public class TestMemoryLeak {

    static class OOMObject{}

    @Test
    public void testOOM(){
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }

    private int stackLength = 1;

    public void stackLead(){
        stackLength ++;
        stackLead();
    }

    @Test
    public void testOOS(){
        TestMemoryLeak oom = new TestMemoryLeak();

        try {
            oom.stackLead();
        } catch (Exception e) {
            log.error("===> stack length: {}", stackLength);
//            throw  e;
        }
    }
}
