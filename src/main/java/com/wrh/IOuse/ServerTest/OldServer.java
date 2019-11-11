package com.wrh.IOuse.ServerTest;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:54 2019/10/17 0017
 * @Modified By:
 */
@Slf4j
public class OldServer {
    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(8898)) {
            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());

                byte[] bytes = new byte[4096];
                // 从socket中读取字节数据
                while (true) {
                    // 读取的字节数大小，-1则表示数据已被读完
                    int readCount = inputStream.read(bytes, 0, bytes.length);
                    if (-1 == readCount) {
                        break;
                    }
                    log.info("===> 读取到的数据为： {}",new String(bytes));
                }
            }
        }
    }
}
