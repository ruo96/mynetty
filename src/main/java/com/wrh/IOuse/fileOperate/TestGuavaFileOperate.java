package com.wrh.IOuse.fileOperate;


import cn.hutool.crypto.SmUtil;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.URISyntaxException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:46 2019/4/4 0004
 * @Modified By:
 */
@Slf4j
public class TestGuavaFileOperate {



    @Test
    public void Test890() {

        /**
         * Guava中的IO
         * 关于IO的内容并不复杂，上面的那些例子在很多工具库中基本都会提供对应的API方便开发者调用，今天主要看下Guava IO模块针对流的操作提供了什么样的 封装
         *
         * Files         *
         * 提供对文件快捷读写方法 其中主要提供了ByteSource、ByteSink、CharSource、CharSink 4个类，分别对应按字节的读写与按字符的读写
         *
         */

    }

    private String root = "e:\\data\\test.txt";

    /**
     * 文件复制
     */
    @Test
    public void copy() throws IOException {
        File from = new File(root,"from");
        File to = new File(root,"to");
        Files.copy(from,to);
    }

    /**
     * 文件移动
     */
    @Test
    public void move() throws IOException {
        File from = new File(root,"from");
        File to = new File(root,"to");
        Files.move(from,to);
    }

    /**
     * 按行读取文件
     * @throws IOException
     */
    @Test
    public void readLines() throws IOException {
        File dest = new File(root,"start.bat");
        List<String> lines = Files.readLines(dest, Charset.defaultCharset());
        lines.forEach(System.out::println);
    }

    /**
     * 写入文件
     * @throws IOException
     */
    @Test
    public void writeToFile() throws IOException {
        File dest = new File(root,"demo.txt");
        Files.write("hello world!".getBytes(Charset.defaultCharset()), dest);
    }

    /**
     * 修改文件更新时间
     * @throws IOException
     */
    @Test
    public void touch() throws IOException {
        File dest = new File(root,"demo.txt");
        Files.touch(dest);
    }

    /**
     * 文件的零拷贝
     * @throws IOException
     */
    @Test
    public void map() throws IOException, URISyntaxException {
        File from = new File(root,"from");
        File to = new File(root,"to");
        Files.touch(to);

        MappedByteBuffer fromBuff = Files.map(from, FileChannel.MapMode.READ_ONLY, 1024);
        // =>
        FileChannel channel = FileChannel.open(Paths.get(to.toURI()), StandardOpenOption.WRITE);

        channel.write(fromBuff);

        channel.close();
    }

    /**
     * 读文件为字节数组
     * @throws IOException
     */
    @Test
    public void fileAndBytes() throws IOException {
        File dest = new File(root,"start.bat");
        ByteSource byteSource = Files.asByteSource(dest);
        byte[] bytes = byteSource.read();
        System.out.println( bytes );

        // 字节写入文件，实现复制
        File target = new File(root, "start2.bat");
        ByteSink byteSink = Files.asByteSink(target);
        byteSink.write(bytes);
    }

    @Test
    public void wrapper(){
        File dest = new File(root,"start.bat");
        // 作为字节读
        Files.asByteSource(dest);
        // 作为字节写
        Files.asByteSink(dest);

        // 作为字符读
        Files.asCharSource(dest, Charset.defaultCharset());
        // 作为字符写
        Files.asCharSink(dest, Charset.defaultCharset());
    }
    
    @Test
    public void Test149() throws IOException {
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream();
        pipedOutputStream.connect(pipedInputStream);

        new Thread(()->{
            while (true){
                String date = new Date().toString();
                try {
                    pipedOutputStream.write( date.getBytes(StandardCharsets.UTF_8) );
                    pipedOutputStream.flush();
                    TimeUnit.SECONDS.sleep(2);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(()->{
            while (true){
                byte [] buff = new byte[1024];
                try {
                    int read = pipedInputStream.read(buff);
                    TimeUnit.SECONDS.sleep(4);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println( new String(buff) );
            }
        }).start();
      
    }

    @Test
    public void Test192() throws IOException {
//        String s = "6D6C624FE01180AD14E53E98B73EC730B4661CAAB3F74371956DF69984647DC9";
//        String s1 = SmUtil.sm3(s);
//        System.out.println("s1 = " + s1);

        List<String> lines = Files.readLines(new File("e:\\data\\800W-200.txt"), Charset.defaultCharset());
        System.out.println("lines = " + lines);
        lines = lines.stream().map(e->SmUtil.sm3(e)).collect(Collectors.toList());
        System.out.println("lines = " + lines);
        lines.forEach(e->{
            try {
                Files.append(e, new File("e:\\data\\800W-200-sm3.txt"), Charset.defaultCharset());
                Files.append("\n", new File("e:\\data\\800W-200-sm3.txt"), Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    @Test
    public void Test215() {
        String s = "0007887FEB0C11EDBACCC03C5902915700078880EB0C11EDA710C03C59029157";
        String s1 = SmUtil.sm3(s);
        System.out.println("s1 = " + s1);

        boolean b = true;

    }
}
