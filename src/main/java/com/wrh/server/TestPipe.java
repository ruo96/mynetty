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
public class TestPipe {

    @Test
    public void testPipe() throws IOException {

        /*获取管道*/
        Pipe pipe = Pipe.open();

        ByteBuffer buf = ByteBuffer.allocate(1024);

        Pipe.SinkChannel sinkChannel = pipe.sink();

        buf.put("通过单向管道发送数据".getBytes());

        buf.flip();

        sinkChannel.write(buf);

        /*读取缓冲区中的数据*/
        Pipe.SourceChannel sourceChannel = pipe.source();
        buf.flip();
        int len = sourceChannel.read(buf);
        System.out.println(new String(buf.array(),0,len));

        sourceChannel.close();
        sinkChannel.close();

    }

    @Test
    public void testId(){
        String a = "1101";
        Map<String,String> map =  new HashMap<>();
        map.put("companyId", a);
        int b;

    }
}
