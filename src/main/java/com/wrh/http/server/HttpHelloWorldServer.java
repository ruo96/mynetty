package com.wrh.http.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author wuruohong
 * @Classname HttpHelloWorldServer
 * @Description TODO
 * @Date 2022/1/4 11:50
 */
public class HttpHelloWorldServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024) // 配置父channle配置
            .childOption(ChannelOption.SO_KEEPALIVE,true) // 配置子channel配置
            .group(bossGroup, workerGroup) // 配置线程组
            .channel(NioServerSocketChannel.class) // 配置 channel 类型
            .handler(new LoggingHandler(LogLevel.INFO)) // 配置父 channel的handler
            .childHandler(new ChannelInitializer<SocketChannel>() {  // 配置子 channel的handler
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new HttpServerCodec());
                    p.addLast(new HttpHelloWorldServerHandler());
                }
            });

            ChannelFuture f = b.bind(8888).sync(); // 绑定端口，启动服务
            f.channel().closeFuture().sync();  // 阻塞等待关闭



        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
