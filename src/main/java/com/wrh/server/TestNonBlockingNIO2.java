package com.wrh.server;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 7:08 2018/7/16 0016
 * @Modified By:
 */
public class TestNonBlockingNIO2 {

    public static void main(String[] args) throws IOException {
        TestNonBlockingNIO2 testNonBlockingNIO2 = new TestNonBlockingNIO2();
        testNonBlockingNIO2.send();
    }


//    @Test
    public void send() throws IOException{
        DatagramChannel dc = DatagramChannel.open();

        dc.configureBlocking(false);

        ByteBuffer buf = ByteBuffer.allocate(1024);

        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()){
            String str = scan.next();
            buf.put(("请输入:"+"---"+str+"===="+LocalDateTime.now().toString()).getBytes());
            buf.flip();
            dc.send(buf,new InetSocketAddress("127.0.0.1",9898));
            buf.clear();
        }
        dc.close();
    }

    @Test
    public void receive() throws IOException {

        DatagramChannel dc = DatagramChannel.open();

        dc.configureBlocking(false);

        dc.bind(new InetSocketAddress(9898));

        Selector selector = Selector.open();

        dc.register(selector,SelectionKey.OP_READ);

        while (selector.select() > 0){
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while(it.hasNext()){
                SelectionKey sk = it.next();

                if(sk.isReadable()){
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    dc.receive(buf);

                    buf.flip();

                    System.out.println(new String(buf.array(),0,buf.limit()));
                    buf.clear();
                }
            }

            it.remove();
        }

    }
}
