package com.wrh.tcp.nianbaochaibao.bufferedreader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author wuruohong
 * @Classname ClientSocket
 * @Description 解决方案1：固定缓冲区大小
 * 固定缓冲区大小的实现方案，只需要控制服务器端和客户端发送和接收字节的（数组）长度相同即可。
 *
 * 从以上代码可以看出，虽然这种方式可以解决粘包和半包的问题，但这种固定缓冲区大小的方式增加了不必要的数据传输，
 * 因为这种方式当发送的数据比较小时会使用空字符来弥补，所以这种方式就大大的增加了网络传输的负担，所以它也不是最佳的解决方案。
 * @Date 2021/1/6 19:13
 */
public class ClientSocketV3 {
    public static void main(String[] args) throws IOException {
        // 启动 Socket 并尝试连接服务器
        Socket socket = new Socket("127.0.0.1", 9092);
        final String message = "Hi,Java."; // 发送消息
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()))) {
            // 给服务器端发送 10 次消息
            for (int i = 0; i < 10; i++) {
                // 注意:结尾的 \n 不能省略,它表示按行写入
                bufferedWriter.write(message + "\n");
                // 刷新缓冲区(此步骤不能省略)
                bufferedWriter.flush();
            }
        }
    }
}
