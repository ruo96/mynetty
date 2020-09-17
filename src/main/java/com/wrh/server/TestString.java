package com.wrh.server;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.HashMap;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:50 2018/7/17 0017
 * @Modified By:
 */
public class TestString {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TestString.class);

    @Test
    public void testString() throws IOException {

        String a = "123";
        String b ="";
        String c = null;
        System.out.println("a: "+a.split(";"));
        System.out.println("b: "+b.split(";"));
        System.out.println("c: "+c.split(";"));

    }

    @Test
    public void testId(){
        String a = "1101";
        Map<String,String> map =  new HashMap<>();
        map.put("companyId", a);
        int b;

    }

    @Test
    public void Test41() {

        String message = null;

        try{
            int i = 10 / 0;
        }catch (Exception e){
            LOGGER.info("message={}", new Object[] { JSON.toJSONString(message), e });
        }

    }
}
