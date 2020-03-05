package com.wrh.thread.threadPool;

import com.wrh.thread.threadPool.task.MyRunnable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:14 2019/11/27 0027
 * @Modified By:
 */
@Slf4j
//@ContextConfiguration(classes = )
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
public class ThreadPoolDemo {

    public static final int CORE_POOL_SIZE = 1;
    public static final int MAX_POOL_SIZE = 2;
    public static final int QUEUE_CAPACITY = 10;
    public static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
//                new ThreadPoolExecutor.CallerRunsPolicy()
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 20 ; i++) {
            Runnable worker = new MyRunnable(""+i);
            executor.execute(worker);
        }

        executor.shutdown();
        while (!executor.isTerminated()){

        }
        log.info("finished all threads!");
    }

    @Test
    public void test1(){
        ReentrantLock lock = new ReentrantLock();
        log.info("===> {}",lock.getHoldCount());
        log.info("===> {}",lock.getQueueLength());

//        lock.tryLock()

    }

    @Value("#{systemProperties['os.name']}")
    String osName;

    @Test
    public void test2(){
        log.info("===> os name:{}" ,osName);

    }

    @Value("#{T(java.lang.Math).random() * 100}")
    String  number;

    @Test
    public void test3(){
        log.info("===> {}",number);

    }

    @Test
    public void test4(){
        String path = ThreadPoolDemo.class.getClassLoader().getResource("").getPath();
        log.info("===> {}",path);

    }

    @Value("classpath:data.txt")
    Resource file;

    @Test
    public void test5() throws IOException {
        log.info("===> file:{}",file.contentLength());
        log.info("===> getFilename:{}",file.getFilename());
        log.info("===> getPath:{}",file.getInputStream());
        InputStream inputStream = file.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        log.info("===> reader.getEncoding():{}",reader.getEncoding());
        log.info("===> reader.read():{}",reader.read());

        FileInputStream fis = null;

        fis = new FileInputStream(file.getFile());
        byte[] buf = new byte[1024]; //数据中转站 临时缓冲区
        int length = 0;
        //循环读取文件内容，输入流中将最多buf.length个字节的数据读入一个buf数组中,返回类型是读取到的字节数。
        //当文件读取到结尾时返回 -1,循环结束。
        while((length = fis.read(buf)) != -1){
            System.out.println(new String(buf, 0, length));
        }



//        Reader reader = new FileReader(inputStream);
//        File file = file.getPath()


    }

    @Value("${cq.key:99998888}")
    private String key1;

    @Test
    public void test6(){
        log.info("===>{}",key1);

    }


}
