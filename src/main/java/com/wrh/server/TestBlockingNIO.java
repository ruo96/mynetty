package com.wrh.server;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 7:08 2018/7/16 0016
 * @Modified By:
 */
public class TestBlockingNIO {

    @Test
    public void client() throws IOException {
        /*获取通道*/
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));

        FileChannel inChannel = FileChannel.open(Paths.get("e:\\1.jpg"), StandardOpenOption.READ);

        /*分配指定大小的缓冲区*/
        ByteBuffer buf = ByteBuffer.allocate(1024);

        /*从本地读取图片发送给远程服务端*/
        while( inChannel.read(buf) != -1){
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }

        /*关闭通道*/
        inChannel.close();
        sChannel.close();

    }

    @Test
    public void server() throws IOException {
        /*获取通道*/
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        FileChannel outChannel = FileChannel.open(Paths.get("e:\\2.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);

        /*绑定连接端口号*/
        ssChannel.bind(new InetSocketAddress(9898));

        /*获取客户端连接的通道*/
        SocketChannel  sChannel = ssChannel.accept();

        /*操作数据的缓冲区*/
        ByteBuffer buf = ByteBuffer.allocate(1024);

        /*接收客户端的数据,并保存到本地*/
        while( sChannel.read(buf) != -1){
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        sChannel.close();
        outChannel.close();
        ssChannel.close();

    }
}
