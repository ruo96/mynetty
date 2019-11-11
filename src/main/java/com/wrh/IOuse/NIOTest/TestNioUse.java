package com.wrh.IOuse.NIOTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:04 2019/10/17 0017
 * @Modified By:
 */
@Slf4j
public class TestNioUse {
    @Test
    public void test(){
        ByteBuffer bb = ByteBuffer.allocate(11);
        bb.mark();
        log.info(String.valueOf(bb.capacity()));
        log.info(String.valueOf(bb.position()));

        bb.put("123".getBytes());
        bb.mark();
        log.info(String.valueOf(bb.position()));

        bb.flip();
        byte[] read = new byte[1024];
        bb.get(read,bb.position(),3);
        log.info("读到的数据为： {}",new String(read));

        bb.clear();
        log.info(String.valueOf(bb.mark()));
    }
}
