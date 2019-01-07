package com.wrh.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:31 2018/11/12 0012
 * @Modified By:
 */
public class TestByteBuf {

    public static void main(String[] args) {

        Charset utf8 = Charset.forName("utf-8");
        ByteBuf buf = Unpooled.copiedBuffer("netty in action rocks!",utf8);
        System.out.println((char) buf.getByte(0));
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        buf.setByte(0,(byte)'B');
        System.out.println((char) buf.getByte(0));

        assert readerIndex == buf.readerIndex();
        assert writerIndex == buf.writerIndex();
    }
}
