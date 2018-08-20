package com.wrh.server;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 7:08 2018/7/16 0016
 * @Modified By:
 */
public class TestNonBlockingNIO {


    public static void main(String[] args) {
//        Scanner s = new Scanner(System.in);
//
//        while (s.hasNext()){
//            String str = s.next();
//            System.out.print(str.toString());
//        }

        TestNonBlockingNIO nio = new TestNonBlockingNIO();
        try {
            nio.client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Test
    public void client() throws IOException {
        /*获取通道 输入对方的端口和ip地址*/
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));

        /*切换成非阻塞模式*/
        sChannel.configureBlocking(false);

        /*分配指定大小的缓冲区*/
        ByteBuffer buf = ByteBuffer.allocate(1024);

        /*从本地把时间发送给远程服务端*/
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){

            String str = scan.next();
            System.out.println("键盘发送数据:"+ str);
            buf.put((LocalDateTime.now().toString() + "\n" + str).getBytes());
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }


        sChannel.close();

    }

    @Test
    public void server() throws IOException {
        /*获取通道*/
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        ssChannel.configureBlocking(false);

        /*绑定连接端口号*/
        ssChannel.bind(new InetSocketAddress(9898));

        /*获取选择器*/
        Selector selector = Selector.open();

        /*将通道注册到选择器上  并且指定监听 接收 事件*/
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        /*通过选择器轮询获取选择器上准备就绪的事件*/
        /*select 是一个阻塞方法,这个就是主线程也使用线程池的方式 netty    nio用的是单线程操作   这个也是netty对比nio的优点之一*/
        while (selector.select() > 0){
            /*获取当前选择器中所有注册的选择键 已就绪的监听事件*/
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            /*迭代获取*/
            while (it.hasNext()){
                /*获取准备就绪的事件*/
                SelectionKey sk = it.next();

                /*判断具体是什么事件准备就绪*/
                if(sk.isAcceptable()){
                    /*获取客户端连接*/
                    SocketChannel sChannel = ssChannel.accept();

                    /*客户端切换非阻塞模式*/
                    sChannel.configureBlocking(false);

                    /*将该通道注册到选择器上*/
                    sChannel.register(selector,SelectionKey.OP_READ);
                }else if(sk.isReadable()){
                    /*先要获取当前选择器上的 读就绪 状态的通道*/
                    SocketChannel sChannel = (SocketChannel) sk.channel();

                    /*读取数据*/
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    while((len = sChannel.read(buf)) > 0){
                        buf.flip();
                        System.out.println(new String(buf.array(),0,len));
                        buf.clear();
                    }
                }
                /*取消选择键*/
                it.remove();
            }
        }




        ssChannel.close();

    }
}
