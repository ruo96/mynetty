package com.wrh.tuling.netty.netty.chatroom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Scanner;

public class ChatroomNettyServerHandler extends ChannelInboundHandlerAdapter {

    public static ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup("ChannelGroups", GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有连接注册上来了：" + ctx.channel().remoteAddress());
        CHANNEL_GROUP.add(ctx.channel());
        ByteBuf buf = Unpooled.copiedBuffer("欢迎来到聊天室", CharsetUtil.UTF_8);
        ctx.channel().writeAndFlush(buf);

        CHANNEL_GROUP.forEach(ch->{
            if(ch != ctx.channel()) {
                ByteBuf buf1 = Unpooled.copiedBuffer("您的好朋友" + ctx.channel().remoteAddress() + "已上线", CharsetUtil.UTF_8);
                ch.writeAndFlush(buf1);
            }
        });
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有连接注销了：" + ctx.channel().remoteAddress());
        CHANNEL_GROUP.remove(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收到连接：" + ctx.name());
//        channels.add(ctx.channel());
        System.out.println("服务端增加channel"+ctx.channel().toString());
    }

    /**
     * 读取客户端发送的数据
     *
     * @param ctx 上下文对象, 含有通道channel，管道pipeline
     * @param msg 就是客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        this.channels.add(ctx.channel());
        System.out.println("服务器读取线程 " + Thread.currentThread().getName());
        //Channel channel = ctx.channel();
        //ChannelPipeline pipeline = ctx.pipeline(); //本质是一个双向链接, 出站入站
        //将 msg 转成一个 ByteBuf，类似NIO 的 ByteBuffer
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(ctx.channel().remoteAddress()+ "客户端发送消息是:" + buf.toString(CharsetUtil.UTF_8));
        String message = buf.toString(CharsetUtil.UTF_8);

        CHANNEL_GROUP.forEach(ch->{
            if(ch == ctx.channel()) {
                ByteBuf buf1 = Unpooled.copiedBuffer("你发了消息：" + message, CharsetUtil.UTF_8);
                ch.writeAndFlush(buf1);
            }else {
                ByteBuf buf2 = Unpooled.copiedBuffer( "聊天室的"+ ctx.channel().remoteAddress() + "发了消息：" + message, CharsetUtil.UTF_8);
                ch.writeAndFlush(buf2);
            }
        });


    }

    /**
     * 数据读取完毕处理方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = Unpooled.copiedBuffer("HelloClient", CharsetUtil.UTF_8);
//        ctx.writeAndFlush(buf);
    }

    /**
     * 处理异常, 一般是需要关闭通道
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
