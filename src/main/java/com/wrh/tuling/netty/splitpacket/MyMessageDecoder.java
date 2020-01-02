package com.wrh.tuling.netty.splitpacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println();
        System.out.println("MyMessageDecoder decode 被调用");
        //需要将得到二进制字节码-> MyMessageProtocol 数据包(对象)
        if(in.readableBytes() >= 4) {
            int length = in.readInt();
            byte[] content = new byte[length];
            in.readBytes(content);

            //封装成MyMessageProtocol对象，传递到下一个handler业务处理
            MyMessageProtocol messageProtocol = new MyMessageProtocol();
            messageProtocol.setLen(length);
            messageProtocol.setContent(content);

            out.add(messageProtocol);
        }
    }
}
