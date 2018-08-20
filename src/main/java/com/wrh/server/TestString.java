package com.wrh.server;

import org.junit.Test;

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
}
