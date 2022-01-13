package com.wrh.zerocopy;

import org.junit.Test;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author wuruohong
 * @Classname TestZeroCopy
 * @Description TODO
 * @Date 2022/1/12 11:45
 */
public class TestZeroCopy {

    /**
     * 零拷贝实际是针对cpu尽量少参与数据传输任务，尽量交给dma来执行
     * 5.1 Java NIO对mmap的支持
     * Java NIO有一个MappedByteBuffer的类，可以用来实现内存映射。它的底层是调用了Linux内核的mmap的API。
     *
     * mmap的小demo如下：
     */
    @Test
    public void Test12() {
        try {
            FileChannel readChannel = FileChannel.open(Paths.get("./jay.txt"), StandardOpenOption.READ);
            MappedByteBuffer data = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, 1024 * 1024 * 40);
            FileChannel writeChannel = FileChannel.open(Paths.get("./siting.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            //数据传输
            writeChannel.write(data);
            readChannel.close();
            writeChannel.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * 5.2 Java NIO对sendfile的支持
     * FileChannel的transferTo()/transferFrom()，底层就是sendfile() 系统调用函数。
     * Kafka 这个开源项目就用到它，平时面试的时候，回答面试官为什么这么快，就可以提到零拷贝sendfile这个点。
     * @Override
     * public long transferFrom(FileChannel fileChannel, long position, long count) throws IOException {
     *    return fileChannel.transferTo(position, count, socketChannel);
     * }
     */
    @Test
    public void Test40() {
        try {
            FileChannel readChannel = FileChannel.open(Paths.get("./jay.txt"), StandardOpenOption.READ);
            long len = readChannel.size();
            long position = readChannel.position();

            FileChannel writeChannel = FileChannel.open(Paths.get("./siting.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            //数据传输
            readChannel.transferTo(position, len, writeChannel);
            readChannel.close();
            writeChannel.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
