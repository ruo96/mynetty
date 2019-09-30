package com.wrh.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:20 2019/9/30 0030
 * @Modified By:
 */
@Slf4j
public class Server {
    private final static int PORT = 8080;

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChildChannelHandler());


            ChannelFuture f = b.bind(PORT).sync();

            log.info("【{}】  服务器开始监听端口，等待客户端连接。。。。",Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private static class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new ServerHandler());
        }
    }

    static class ServerHandler extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            byte[] reg = new byte[buf.readableBytes()];
            buf.readBytes(reg);
            String body = new String(reg, StandardCharsets.UTF_8);
            System.out.println(Thread.currentThread().getName() +
                    ",The server receive  order : " + body);

            String respMsg = "I am Server，消息接收 success!";
            ByteBuf respByteBuf = Unpooled.copiedBuffer(respMsg.getBytes());
            ctx.write(respByteBuf);
        }
    }
}
