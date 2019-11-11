package com.wrh.IOuse.ServerTest;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 7:24 2019/10/17 0017
 * @Modified By:
 */
@Slf4j
public class NewServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(8899));

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);  //这里设置为阻塞模式
            int readCount = socketChannel.read(byteBuffer);

            while( -1 != readCount){
                readCount = socketChannel.read(byteBuffer);

                //这里一定要用下rewind方法，将position重置为0开始位置
                byteBuffer.rewind();
            }
        }
    }
}
