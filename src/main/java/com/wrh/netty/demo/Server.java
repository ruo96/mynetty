package com.wrh.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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

        // 创建 BossGroup 和 WorkerGroup
        // 1. bossGroup 只处理连接请求
        // 2. 业务处理由 workerGroup 来完成
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(12);

        try {
            // 创建服务器端的启动对象
            /**
             * 1）Bootstrap 和 ServerBootstrap 分别是客户端和服务器端的引导类，
             * 一个 Netty 应用程序通常由一个引导类开始，主要是用来配置整个 Netty 程序、设置业务处理类（Handler）、绑定端口、发起连接等。
             */
            ServerBootstrap b = new ServerBootstrap();
            // 配置参数 // 设置线程组
            b.group(bossGroup,workerGroup)
                    // 说明服务器端通道的实现类（便于 Netty 做反射处理）
                    /** 3）服务端首先创建一个 NioServerSocketChannel 作为服务器端通道，每当接收一个客户端连接就产生一个 NioSocketChannel 应对该客户端。*/
                    .channel(NioServerSocketChannel.class)
                    // 设置等待连接的队列的容量（当客户端连接请求速率大
                    // 于 NioServerSocketChannel 接收速率的时候，会使用
                    // 该队列做缓冲）
                    // option()方法用于给服务端的 ServerSocketChannel
                    // 添加配置
                    .option(ChannelOption.SO_BACKLOG,1024)
                    // 设置连接保活
                    // childOption()方法用于给服务端 ServerSocketChannel
                    // 接收到的 SocketChannel 添加配置
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // handler()方法用于给 BossGroup 设置业务处理器
                    // childHandler()方法用于给 WorkerGroup 设置业务处理器
                    .childHandler(new ChildChannelHandler());


            // 绑定端口，启动服务器，生成一个 channelFuture 对象，
            // ChannelFuture 涉及到 Netty 的异步模型
            ChannelFuture channelFuture = b.bind(PORT).sync();

            log.info("【{}】  服务器开始监听端口，等待客户端连接。。。。",Thread.currentThread().getName());
            // 对通道关闭进行监听
            channelFuture.channel().closeFuture().sync();
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
            // 向 Pipeline 添加业务处理器
            socketChannel.pipeline().addLast(new ServerHandler());
            // 可以继续调用 socketChannel.pipeline().addLast()
            // 添加更多 Handler
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

        /**
         * 数据读取完毕后执行
         *
         * @param ctx 上下文对象
         * @throws Exception
         */
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx)
                throws Exception {
            // 发送响应给客户端
            ctx.writeAndFlush(
                    // Unpooled 类是 Netty 提供的专门操作缓冲区的工具
                    // 类，copiedBuffer 方法返回的 ByteBuf 对象类似于
                    // NIO 中的 ByteBuffer，但性能更高
                    Unpooled.copiedBuffer(
                            "hello client! i have got your data.",
                            CharsetUtil.UTF_8
                    )
            );
        }

        /**
         * 发生异常时执行
         *
         * @param ctx   上下文对象
         * @param cause 异常对象
         * @throws Exception
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
                throws Exception {
            // 关闭与客户端的 Socket 连接
            ctx.channel().close();
        }
    }

    @Test
    public void Test138() {
        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}
