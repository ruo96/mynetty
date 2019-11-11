package com.wrh.IOuse.ServerTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 7:29 2019/10/17 0017
 * @Modified By:
 */
@Slf4j
public class NewClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8899));

        socketChannel.configureBlocking(true);

        String fileName = "e:\\file\\bigtext.txt";

        FileInputStream fileInputStream =  new FileInputStream(fileName);
        FileChannel fileChannel = fileInputStream.getChannel();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        long transferCount = fileChannel.transferTo(0,fileChannel.size(),socketChannel); //目标channel
        stopWatch.stop();
        log.info("===> 发送的字节数： {}   耗时：{}", transferCount, stopWatch.getTotalTimeSeconds());
        fileChannel.close();


    }
}
