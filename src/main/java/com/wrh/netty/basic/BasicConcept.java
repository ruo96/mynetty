package com.wrh.netty.basic;

import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:35 2019/11/14 0014
 * @Modified By:
 */
@Slf4j
public class BasicConcept {

    @Test
    public void test1(){
        /*ByteBuf buf = ByteBufAllocator;

        CompositeByteBuf*/

    }

    @Test
    public void test2(){
        int i = -54;
        int b = i - 1;
        b |= b >>>1;
        b |= b >>>2;
        b |= b >>>4;
        b |= b >>>8;
        b |= b >>>16;
        System.out.println(b+1);

        int j = 14;
        int k = -14;
        System.out.println(j>>>1);
        System.out.println(k>>1);
    }

    @Test
    public void test3(){
        int i = 999999999;
        int b = i - 1;
        b |= b >>1;
        b |= b >>2;
        b |= b >>4;
        b |= b >>8;
        b |= b >>16;
        System.out.println(b+1);
    }

    @Test
    public void test4(){
        String a = "0";
        System.out.println(a.hashCode());

        char[] achar = a.toCharArray();
        System.out.println("legnth: " + achar.length);
        System.out.println(achar[0] + 31);
        System.out.println(achar[0]);


        /*int h = 0;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;*/
        HashMap<String, String> map = new HashMap<>();

    }

    @Test
    public void test5(){
        String a = "abcd";
        String b = "dcba";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        String c = "";
        System.out.println(c.hashCode());
    }

    @Test
    public void test6(){
        String a = "123";
        System.out.println(a.hashCode());
    }

    @Test
    public void test7(){
        char a = '1';
        System.out.println(1+a);

        Integer i = 12345;
        System.out.println(i.hashCode());
    }

    @Test
    public void Test106() throws IOException {
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel channel = serverSocketChannel.accept();
//        NioSocketChannel channel1 = channel;
        /** 线程*/
        NioEventLoop loop;
        /** 线程池*/
        NioEventLoopGroup loop1;
        Selector selector;

        /**
         * 每个 BossNioEventLoop 中循环执行以下三个步骤：
         * 5.1）select：轮训注册在其上的 ServerSocketChannel 的 accept 事件（OP_ACCEPT 事件）
         * 5.2）processSelectedKeys：处理 accept 事件，与客户端建立连接，生成一个 NioSocketChannel，
         *      并将其注册到某个 WorkerNioEventLoop 上的 Selector 上
         * 5.3）runAllTasks：再去以此循环处理任务队列中的其他任务
         */

        /**
         * 每个 WorkerNioEventLoop 中循环执行以下三个步骤：
         * 6.1）select：轮训注册在其上的 NioSocketChannel 的 read/write 事件（OP_READ/OP_WRITE 事件）
         * 6.2）processSelectedKeys：在对应的 NioSocketChannel 上处理 read/write 事件
         * 6.3）runAllTasks：再去以此循环处理任务队列中的其他任务
         */

        /**
         * 以上两个processSelectedKeys步骤中，会使用 Pipeline（管道），Pipeline 中引用了 Channel，
         * 即通过 Pipeline 可以获取到对应的 Channel，Pipeline 中维护了很多的处理器（拦截处理器、过滤处理器、自定义处理器等）。
         */
    }
}
