package com.wrh.tuling.netty.netty.chatroom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.springframework.scheduling.annotation.Async;

import java.util.Scanner;

public class ChatroomNettyClientHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println(s);
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while ( scanner.hasNextLine() ) {
                    ctx.channel().writeAndFlush(scanner.nextLine());
                }
            }
        }).start();*/
    }



    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        /*while(true) {*/
            /*Scanner scanner = new Scanner(System.in);
            if( scanner.hasNext()) {
                ByteBuf buf = Unpooled.copiedBuffer(scanner.next(), CharsetUtil.UTF_8);
                System.out.println("本机发送了信息:" + buf.toString(CharsetUtil.UTF_8));
                ctx.channel().writeAndFlush(buf);
//                break;

            }*/
        /*}*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
