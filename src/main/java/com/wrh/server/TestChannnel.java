package com.wrh.server;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:15 2018/7/12 0012
 * @Modified By:
 */
@Slf4j
public class TestChannnel {

    ByteBuffer buf;
    long start;
    long end;

    @Before
    @Test
    public void testBefore(){
   /*分配指定大小的缓冲区*/
    start = System.currentTimeMillis();

    }

    /*非直接缓冲区*/
    @Test
    public void test(){
        buf = ByteBuffer.allocate(1024);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("e:\\1.pdf");
            fos = new FileOutputStream("e:\\2.pdf");

        /*获取通道*/
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();



        /*将通道的数据存入缓冲区*/
            while (inChannel.read(buf) != -1){
                buf.flip();/*切换成读取数据的模式*/

                /*将缓冲区中的数据写入通道中*/
                outChannel.write(buf);
                buf.clear();/*清空缓冲区*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(outChannel != null){
                try{
                    outChannel.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            if(inChannel != null){
                try{
                    inChannel.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            if(fos != null){
                try{
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            if(fis != null){
                try{
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }


    }

    @Test
    public void test1(){

        /*内存映射文件的方式*/
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        MappedByteBuffer inMappedBuf = null;
        MappedByteBuffer outMappedBuf = null;
        try {
            inChannel = FileChannel.open(Paths.get("e:\\1.pdf"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("e:\\3.pdf"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE_NEW);

        /*内存映射 文件*/
            inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY,0,inChannel.size());
            outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE,0,inChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
            try {
                inChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                outChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        /*直接对缓冲区进行数据的读写操作*/
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);


    }



    @After
    @Test
    public void testAfter(){
        end = System.currentTimeMillis();
        log.info("耗费时间:{}",end - start);
    }
}
