package com.wrh.server;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:01 2018/7/12 0012
 * @Modified By:
 */
@Slf4j
public class TestBuffer {

    ByteBuffer b;
    String s1;
    @Test
    @Before
    public void test(){
        b = ByteBuffer.allocate(1024);
    }

    @Test
    public void testPut(){
        s1 = new String("abcde");
        b.put(s1.getBytes());
    }

    @Test
    public void testPut1(){
        s1 = new String("fg");
        b.put(s1.getBytes());
    }

    @Test
    public void testFlip(){
        b.flip();
    }

    @Test
    public void testRead(){
        testPut();
        testFlip();
    }

    @Test
    public void testReadByte(){
        testPut();
        testFlip();
        byte[] dst = new byte[b.limit()];
        b.get(dst);
        log.info("dst中的数据为:{}",new String(dst, 0, dst.length));
    }

    @Test
    public void testRewind(){
        /*重复读的模式*/
        testReadByte();
        b.rewind();
    }

    @Test
    public void testClear(){
        testReadByte();
        b.clear();
        /*数据依然存在,当时处于遗忘状态*/
        log.info("clear之后, b中数据:{}",(char)(b.get()));
    }

    @Test
    public void testMark(){
        /*mark标记*/
        testPut();
        b.mark();
        testPut1();
        b.reset();
//        log.info("mark位置时候的数据:{}",(char)b.get());

    }

    @Test
    public void testRemain(){
        testMark();
        b.flip();
        if(b.hasRemaining()){
            log.info("缓冲区还有{}个数据",b.remaining());
        }
    }

    @Test
    @After
    public void testShow(){
        log.info("当前位置为:{}",b.position());
        log.info("读取限制为:{}",b.limit());
        log.info(" :{}",b.capacity());
    }
}
