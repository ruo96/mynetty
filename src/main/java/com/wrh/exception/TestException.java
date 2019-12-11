package com.wrh.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:13 2019/12/11 0011
 * @Modified By:
 */
@Slf4j
public class TestException {
    @Test
    public void test(){
        try{
            throw new ExceptionB("b");
        }catch (ExceptionA e){
            log.info("a");
        }catch (Exception e) {
            log.info("e");
        }
    }
}
