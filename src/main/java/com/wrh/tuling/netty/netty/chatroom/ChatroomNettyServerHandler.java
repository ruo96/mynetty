package com.wrh.tuling.netty.netty.chatroom;

import freemarker.template.SimpleDate;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ChatroomNettyServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup("ChannelGroups", GlobalEventExecutor.INSTANCE);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /*@Override
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
    }*/

    /*@Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有连接注销了：" + ctx.channel().remoteAddress());
        CHANNEL_GROUP.remove(ctx.channel());
    }*/

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收到连接：" + ctx.name());

        Channel channel = ctx.channel();

        CHANNEL_GROUP.writeAndFlush("[客户端]"+ channel.remoteAddress() +"上线了" +
                sdf.format(new Date()) +"\n");

        CHANNEL_GROUP.add(channel);
        System.out.println(ctx.channel().remoteAddress() +"上线了" + "\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        CHANNEL_GROUP.writeAndFlush("[客户端]" + channel.remoteAddress() +"下线了"+"\n");
        System.out.println(ctx.channel().remoteAddress() + "下线了" + "\n");
        System.out.println("channelgroup size is: " + CHANNEL_GROUP.size());
    }

    /**
     * 读取客户端发送的数据
     *
     * @param ctx 上下文对象, 含有通道channel，管道pipeline
     * @param msg 就是客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        CHANNEL_GROUP.forEach(ch->{
            if(ch != channel) {
                ch.writeAndFlush("聊天室的"+ ctx.channel().remoteAddress() + "发了消息：" + msg + "\n");
            }else {
                ch.writeAndFlush("你发了消息：" + msg +"\n");
            }
        });


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
