package com.wrh.encodeChinese;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:17 2019/8/28 0028
 * @Modified By:
 */
@Slf4j
public class TestUnicode {

    @Test
    public void testUnicode() throws UnsupportedEncodingException {
        String s = "我热爱编程";

        byte[] bytes = s.getBytes(Charset.forName("GBK"));

        log.info(" GBK 编码，GBK解码：{}", new String(bytes, "GBK"));
        log.info(" GBK 编码，GB18030解码：{}", new String(bytes, "GB18030"));
        log.info(" GBK 编码，utf-8解码：{}", new String(bytes, "UTF-8"));

        Integer a = 1000;
        Integer b = 1000;
        log.info(" a == b ?{}", a == b);
        if( a != b ){
            log.info("不等 ");
        }else {
            log.info("等 ");

        }

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        log.info("============forEach============");
        final int[] i = {1};
        list.forEach(e->{
            log.info("当前值为:{}",e);
            log.info("当前sortCode： {}", i[0]);
            i[0]++;
            log.info("===>当前线程名称:{} 线程id：{}",Thread.currentThread().getName(),Thread.currentThread().getId());

        });

        log.info("============stream.forEach============");
        final int[] i1 = {1};
        list.stream().forEach(e->{
            log.info("当前值为:{}",e);
            log.info("当前sortCode： {}", i1[0]);
            i1[0]++;
            log.info("===>当前线程名称:{} 线程id：{}",Thread.currentThread().getName(),Thread.currentThread().getId());

        });

        log.info("============parallelStream.forEach============");
        final int[] i2 = {1};
        list.parallelStream().forEach(e->{
            log.info("当前值为:{}",e);
            log.info("当前sortCode： {}", i2[0]);
            i2[0]++;
            log.info("===>当前线程名称:{} 线程id：{}",Thread.currentThread().getName(),Thread.currentThread().getId());

        });



    }

    @Test
    public void test() throws UnsupportedEncodingException {

        String s1 = "你好";
        String s2 = new String(s1.getBytes("GB2312"), "ISO-8859-1");

        log.info("===> {}",s1);
        log.info("===> {}",s2);

    }

}
