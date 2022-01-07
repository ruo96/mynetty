package com.wrh.http.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author wuruohong
 * @Classname HttpHelloWorldServer
 * @Description TODO
 * @Date 2022/1/4 11:50
 */
public class HttpHelloWorldServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   msg = " + msg);
        ctx.fireChannelRead(msg);
    }
}
