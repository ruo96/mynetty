package com.wrh.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

/**
 * @author Administrator
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:55 2018/7/10 0010
 * @Modified By:
 */
@Service
public class EchoServer {
    /*private  int port;

    public EchoServer(int port1){
        this.port = port1;
    }*/

   /* public  void active(int port1) throws Exception {
        *//*if(args.length != 1){
            System.err.print(
                    "Usage:" + EchoServer.class.getSimpleName() +
                            "<port>"
            );

            int port = Integer.parseInt(args[0]);

        }*//*
//        new EchoServer(port1).start();
    }*/

    public void active(int port) throws Exception{
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }
}
