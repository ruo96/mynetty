package com.wrh.tuling.netty.netty.chatroom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

public class ChatroomNettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当客户端连接服务器完成就会触发该方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端准备发送数据");
        /*ByteBuf buf = Unpooled.copiedBuffer("HelloServer", CharsetUtil.UTF_8);
        ctx.writeAndFlush(buf);*/
    }

    //当通道有读取事件时会触发，即服务端发送数据给客户端
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("收到服务端的消息:" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务端的地址： " + ctx.channel().remoteAddress());


    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        /*while(true) {*/
            Scanner scanner = new Scanner(System.in);
            if( scanner.hasNext()) {
                ByteBuf buf = Unpooled.copiedBuffer(scanner.next(), CharsetUtil.UTF_8);
                System.out.println("本机发送了信息:" + buf.toString(CharsetUtil.UTF_8));
                ctx.channel().writeAndFlush(buf);
//                break;

            }
        /*}*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
