package com.wrh.IOuse.ServerTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:56 2019/10/17 0017
 * @Modified By:
 */
@Slf4j
public class OldClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 8898));

        try(FileInputStream fileInputStream = new FileInputStream(new File("e:\\file\\bigtext.txt"));
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());){

            //读取文件数据
            //定义byte缓存
            byte[] buffer = new byte[4096];
            int readCount; //每一次读取的字节数
            int total = 0; //读取的总字节数

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while((readCount = fileInputStream.read(buffer)) > 0){
                total += readCount; //累加字节数
                dataOutputStream.write(buffer);  //写入到输出流中
            }
            stopWatch.stop();
            log.info(" 发送的粽子结束： {}  共耗时： {}秒", total, stopWatch.getTotalTimeSeconds());
        }
    }
}
