package com.wrh.server;

import com.wrh.utils.DLP;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.nio.cs.CharsetMapping;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:01 2018/7/12 0012
 * @Modified By:
 */
@Slf4j
public class TestDirectBuffer {

    ByteBuffer b;
    String s1;
    @Test
    @Before
    public void test(){
        b = ByteBuffer.allocateDirect(1024);
    }


    @Test
    public void testPut(){
        s1 = new String("abcde");
        b.put(s1.getBytes());
    }

    @Test
    public void testPut1(){
        s1 = new String("fg");
        b.put(s1.getBytes());
    }

    @Test
    public void testFlip(){
        b.flip();
    }

    @Test
    public void testRead(){
        testPut();
        testFlip();
    }

    @Test
    public void testReadByte(){
        testPut();
        testFlip();
        byte[] dst = new byte[b.limit()];
        b.get(dst);
        log.info("dst中的数据为:{}",new String(dst, 0, dst.length));
    }

    @Test
    public void testRewind(){
        /*重复读的模式*/
        testReadByte();
        b.rewind();
    }

    @Test
    public void testClear(){
        testReadByte();
        b.clear();
        /*数据依然存在,当时处于遗忘状态*/
        log.info("clear之后, b中数据:{}",(char)(b.get()));
    }

    @Test
    public void testMark(){
        /*mark标记*/
        testPut();
        b.mark();
        testPut1();
        b.reset();
//        log.info("mark位置时候的数据:{}",(char)b.get());

    }

    @Test
    public void testRemain(){
        testMark();
        b.flip();
        if(b.hasRemaining()){
            log.info("缓冲区还有{}个数据",b.remaining());
        }
    }

    @Test
    public void testDirect(){
        log.info("判断是否是直接缓冲区:{}",b.isDirect());
    }

    @Test
    public void testChannel(){
        /*通道之间的数据传输*/
        try {
            FileChannel in = FileChannel.open(Paths.get("e:/1.pdf"), StandardOpenOption.READ);
            FileChannel out = FileChannel.open(Paths.get("e:/2.pdf"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE_NEW);

//            in.transferTo(0,in.size(),out);
            out.transferFrom(in,0,in.size());

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void testscatterGather(){

        try {
            RandomAccessFile raf1 = new RandomAccessFile("e:/1.txt","rw");

            FileChannel ch1 = raf1.getChannel();

            ByteBuffer buf1 = ByteBuffer.allocate(101);
            ByteBuffer buf2 = ByteBuffer.allocate(1024);

            ByteBuffer[] bufs = {buf1,buf2};
            ch1.read(bufs);


            for(ByteBuffer byteBuffer : bufs){
                byteBuffer.flip();
            }
            log.info("第一批:{}",new String(bufs[0].array(),0,bufs[0].limit()));
            log.info("-------------------------------------------");
            log.info("第二批:{}",new String(bufs[1].array(),0,bufs[1].limit()));


            RandomAccessFile raf2 = new RandomAccessFile("e:/2.txt","rw");
            FileChannel ch2 = raf2.getChannel();
            ch2.write(bufs);

        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @Test
    public void testCharSet(){
        log.info("支持的字符集:{}",Charset.availableCharsets());
        try {
            Map<String,Charset> map = Charset.availableCharsets();

            Set<Map.Entry<String,Charset>>  set = map.entrySet();
            for(Map.Entry<String,Charset> entry:set){
                log.info("输出:{}={}",entry.getKey(),entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDLP(){
        log.info("rndom:{}",String.valueOf(DLP.create(16).genR()));

    }

    @Test
    public void testE(){
        Charset cs1 = Charset.forName("GBK");

        CharsetEncoder ce = cs1.newEncoder();
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cf = CharBuffer.allocate(1024);
        cf.put("尚硅谷威武!");
        cf.flip();

        /*编码*/
        ByteBuffer bf = null;

        try {
            bf = ce.encode(cf);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }

        for(int i = 0;i<10; i++){
            log.info("显示:{}",bf.get());
        }

        /*解码*/
        bf.flip();
        CharBuffer cb2 = null;
        try {
            cb2 = cd.decode(bf);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        log.info("解码后的数据为:{}", cb2.toString());
    }


    @Test
    @After
    public void testShow(){
        log.info("当前位置为:{}",b.position());
        log.info("读取限制为:{}",b.limit());
        log.info(" :{}",b.capacity());
    }
}
