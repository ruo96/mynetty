package com.wrh.tcp.nianbaochaibao.origin;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author wuruohong
 * @Classname ClientSocket
 * @Description TODO
 * @Date 2021/1/6 19:13
 */
public class ClientSocket {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 创建 Socket 客户端并尝试连接服务器端
        Socket socket = new Socket("127.0.0.1", 9999);
        // 发送的消息内容
        final String message = "Hi,Java.";
        // 使用输出流发送消息
        try (OutputStream outputStream = socket.getOutputStream()) {
            // 给服务器端发送 10 次消息
            for (int i = 0; i < 10; i++) {
                // 发送消息
                outputStream.write(message.getBytes());
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}
