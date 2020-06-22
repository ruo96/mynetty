package com.wrh.IOuse.fileOperate;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:46 2019/4/4 0004
 * @Modified By:
 */
@Slf4j
public class TestFileOperate {


    @Test
    public void test() throws IOException {
        String path = (new File("/home/zcs/public")).getAbsolutePath();
        System.out.println(path);

        String txt = "123456\n789\n101112";

        Files.write(Paths.get("/home/test.txt"), txt.getBytes(), StandardOpenOption.CREATE);


        String filePath = "e:\\file\\zijuan1.txt";
//        File file = new File(filePath);
        Set<String> set = new HashSet<>();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                set.add(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("set size is : {}"+set.size());
    }

    @Test
    public void test1(){
        File f = new File("E:"+File.separator+"codes");
        getFileList(f);
    }

    public static void getFileList(File file){
        //第一级子目录
        File[] files = file.listFiles();
        for(File f:files){
            //打印目录和文件
            System.out.println(f);
            if(f.isDirectory()){
                getFileList(f);
            }
        }
    }

    @Test
    public void test2(){
        //不使用 Java 提供的分隔符字段，注意：这样写只能在 Windows 平台有效
        File f1 = new File("D:\\IO\\a.txt");
        //使用 Java 提供的分隔符
        File f2 = new File("D:"+File.separator+"IO"+File.separator+"a.txt");
        System.out.println(f1);//输出 D:\IO\a.txt
        System.out.println(f2);//输出 D:\IO\a.txt

        //File(File parent, String child)
        //从父抽象路径名和子路径名字符串创建新的 File实例。
        File f3 = new File("D:");
        File f4 = new File(f3,"IO");
        System.out.println(f4); //D:\IO

        //File(String pathname)
        //通过将给定的路径名字符串转换为抽象路径名来创建新的 File实例。
        File f5 = new File("D:"+File.separator+"IO"+File.separator+"a.txt");
        System.out.println(f5); //D:\IO\a.txt

        //File(String parent, String child)
        //从父路径名字符串和子路径名字符串创建新的 File实例。
        File f6 = new File("D:","IO\\a.txt");
        System.out.println(f6); //D:\IO\a.txt
    }

    /**
     * File 类的常用方法
     *
     * 　　①、创建方法
     *
     * 　　　　1.boolean createNewFile() 不存在返回true 存在返回false
     * 　　　　2.boolean mkdir() 创建目录，如果上一级目录不存在，则会创建失败
     * 　　　　3.boolean mkdirs() 创建多级目录，如果上一级目录不存在也会自动创建
     *
     *
     *
     * 　　②、删除方法
     *
     * 　　　　1.boolean delete() 删除文件或目录，如果表示目录，则目录下必须为空才能删除
     * 　　　　2.boolean deleteOnExit() 文件使用完成后删除
     *
     *
     *
     * 　　③、判断方法
     *
     * 　　　　1.boolean canExecute()判断文件是否可执行
     * 　　　　2.boolean canRead()判断文件是否可读
     * 　　　　3.boolean canWrite() 判断文件是否可写
     * 　　　　4.boolean exists() 判断文件或目录是否存在
     * 　　　　5.boolean isDirectory()  判断此路径是否为一个目录
     * 　　　　6.boolean isFile()　　判断是否为一个文件
     * 　　　　7.boolean isHidden()　　判断是否为隐藏文件
     * 　　　　8.boolean isAbsolute()判断是否是绝对路径 文件不存在也能判断
     *
     *
     *
     *  　　④、获取方法
     *
     * 　　　　1.String getName() 获取此路径表示的文件或目录名称
     * 　　　　2.String getPath() 将此路径名转换为路径名字符串
     * 　　　　3.String getAbsolutePath() 返回此抽象路径名的绝对形式
     * 　　　　4.String getParent()//如果没有父目录返回null
     * 　　　　5.long lastModified()//获取最后一次修改的时间
     * 　　　　6.long length() 返回由此抽象路径名表示的文件的长度。
     * 　　　　7.boolean renameTo(File f) 重命名由此抽象路径名表示的文件。
     * 　　　　8.File[] liseRoots()//获取机器盘符
     * 　　　　9.String[] list()  返回一个字符串数组，命名由此抽象路径名表示的目录中的文件和目录。
     * 　　　　10.String[] list(FilenameFilter filter) 返回一个字符串数组，命名由此抽象路径名表示的目录中满足指定过滤器的文件和目录
     */
    @Test
    public void test3() throws IOException {
        //File(File parent, String child)
        //从父抽象路径名和子路径名字符串创建新的 File实例。
        File dir = new File("D:"+File.separator+"IO");
        File file = new File(dir,"a.txt");

        //判断dir 是否存在且表示一个目录
        if(!(dir.exists()||dir.isDirectory())){
            //如果 dir 不存在，则创建这个目录
            dir.mkdirs();
            //根据目录和文件名，创建 a.txt文件
            file.createNewFile();

        }
        //返回由此抽象路径名表示的文件或目录的名称。 这只是路径名称序列中的最后一个名字。 如果路径名的名称序列为空，则返回空字符串。
        System.out.println(file.getName()); //a.txt
        //返回此抽象路径名的父null的路径名字符串，如果此路径名未命名为父目录，则返回null。
        System.out.println(file.getParent());//D:\IO
        //将此抽象路径名转换为路径名字符串。 结果字符串使用default name-separator character以名称顺序分隔名称。
        System.out.println(file.getPath()); //D:\IO\a.txt
    }


    /**
     * 第一种方式 读文件
     */
    @Test
    public void test4() throws IOException {

        String filePath = "e:\\file\\gzip.txt";

        StringBuffer sb = new StringBuffer();
        char[] buf = new char[1024];

        FileReader fr = new FileReader(filePath);
        while(fr.read(buf) > 0){
            sb.append(buf);
        }
        log.info(" txt content: {}",sb.toString());

    }
    /**
     * 第二种方式 读文件方式
     * 读取和写入文件I/O操作都是调用操作系统提供的接口，因为磁盘设备是由操作系统管理的，
     * 应用程序要访问物理设备只能通过系统调用的方式来工作。读和写分别对应Read()和Write()两个系统调用。
     * 而只要是系统调用就可能存在内核空间地址和用户空间地址切换的问题，操作系统为了保护系统本身的运行安全，
     * 而将内核程序运行使用的内存空间和用户程序运行的内存空间进行隔离。但会带来从内核空间向用户空间复制数据的问题，
     * 例如：数据不一致和耗时问题，此时为了加速I/O访问，在内核空间使用缓存机制，也就是将从磁盘读取的文件按照一定的组织方式进行缓存，
     * 如果用户访问的是同一磁盘地址的空间数据。那么操作系统将从内核缓存中直接取出返回给用户程序
     */
    @Test
    public void test5() throws IOException {

        String filePath = "e:\\file\\gzip.txt";


        StringBuffer sb  = new StringBuffer();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(" txt content: {}",sb.toString());

    }

    /**
     * 测试写入文件数据
     * @throws IOException
     */
    @Test
    public void test6() throws IOException {

        String filePath = "e:\\file\\gzip.txt";
        StringBuffer sb  = new StringBuffer();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(" txt content: {}",sb.toString());

        Files.write(Paths.get("e:\\file\\gzip_copy.txt"), sb.toString().getBytes("GBK"), StandardOpenOption.CREATE);

    }

    /**
     * 使用缓冲组件对文件I/O进行包装，可以有效提升文件I/O的性能。
     * 直接使用InputStream和OutputStream进行文件读写的代码：
     */
    @Test
    public void testOutAndInputStream(){
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("e:\\file\\data.txt"));
            long start = System.currentTimeMillis();
            for(int i=0;i<10000;i++){
                dataOutputStream.writeBytes(Objects.toString(i)+"\r\n");
            }
            dataOutputStream.close();
            long useTime = System.currentTimeMillis()-start;
            System.out.println("写入数据--useTime:"+useTime);
            //开始读取数据
            long startInput = System.currentTimeMillis();
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream("e:\\file\\data.txt"));

            while (dataInputStream.readLine() != null){
            }
            dataInputStream.close();
            long useTimeInput = System.currentTimeMillis()-startInput;
            System.out.println("读取数据--useTimeInput:"+useTimeInput);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 使用缓冲的代码如下
     * 通过运行结果，我们能很明显的看出来使用缓冲的代码，无论在读取还是写入文件上，性能都有了数量级的提升。
     */
    @Test
    public void testBufferedStream(){

        try {
            DataOutputStream dataOutputStream = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream("e:\\file\\data.txt")));
            long start = System.currentTimeMillis();
            for(int i=0;i<10000;i++){
                dataOutputStream.writeBytes(Objects.toString(i)+"\r\n");
            }
            dataOutputStream.close();
            long useTime = System.currentTimeMillis()-start;
            System.out.println("写入数据--useTime:"+useTime);
            //开始读取数据
            long startInput = System.currentTimeMillis();
            DataInputStream dataInputStream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream("e:\\file\\data.txt")));

            while (dataInputStream.readLine() != null){
            }
            dataInputStream.close();
            long useTimeInput = System.currentTimeMillis()-startInput;
            System.out.println("读取数据--useTimeInput:"+useTimeInput);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 使用Wirter和Reader也有类似的效果
     */
    @Test
    public void testWriterAndReader(){

        try {
            long start = System.currentTimeMillis();
            FileWriter fileWriter = new FileWriter("e:\\file\\data.txt");
            for (int i=0;i<100000;i++){
                fileWriter.write(Objects.toString(i)+"\r\n");
            }
            fileWriter.close();
            long useTime = System.currentTimeMillis()-start;
            System.out.println("写入数据--useTime:"+useTime);
            //开始读取数据
            long startReader = System.currentTimeMillis();
            FileReader fileReader = new FileReader("e:\\file\\data.txt");
            while (fileReader.read() != -1){
            }
            fileReader.close();
            long useTimeInput = System.currentTimeMillis()-startReader;
            System.out.println("读取数据--useTimeInput:"+useTimeInput);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 通过运行结果可以看出，使用了缓冲后，无论是FileReader还是FileWriter的性能都有较为明显的提升。
     *
     * 在上面的例子中，由于FileReader和FilerWriter的性能要优于直接使用FileInputStream和FileOutputStream所以循环次数增加了10倍。
     */
    @Test
    public void testBufferedWriterAndReader(){

        try {
            long start = System.currentTimeMillis();
            BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter("e:\\file\\data.txt"));
            for (int i=0;i<100000;i++){
                fileWriter.write(Objects.toString(i)+"\r\n");
            }
            fileWriter.close();
            long useTime = System.currentTimeMillis()-start;
            System.out.println("写入数据--useTime:"+useTime);
            //开始读取数据
            long startReader = System.currentTimeMillis();
            BufferedReader fileReader = new BufferedReader(
                    new FileReader("e:\\file\\data.txt"));
            while (fileReader.read() != -1){
            }
            fileReader.close();
            long useTimeInput = System.currentTimeMillis()-startReader;
            System.out.println("读取数据--useTimeInput:"+useTimeInput);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 流式处理file
     */
    @Test
    public void Test() {
        try(Stream<String> stream = Files.lines(Paths.get("e:\\file\\test.txt"))) {
            stream.forEach(System.out::println);
        }catch (IOException e) {

        }
    }

    /**
     * 带着格式读取
     * @throws IOException
     */
    @Test
    public void Test375() throws IOException {
        String multiLines = new String(Files.readAllBytes(Paths.get("e:\\file\\data.txt")));
        System.out.println(multiLines);

    }

    /**
     * 删除字符串最后一个字符的多种方法
     */
    @Test
    public void Test386() {
        String str = "abcd";
        System.out.println(str.substring(0, str.length() - 1));
        System.out.println(StringUtils.chop(str));

        /** 正则 没有判空*/
        System.out.println(str.replaceAll(".$", ""));

        /** 正则， java8写法， 判空 优雅*/
        String str2 = Optional.ofNullable(str).map(s-> s.replaceAll(".$", "")).orElse(str);
        System.out.println(str2);

    }

    /**
     * 判断字符在字符串中出现的次数
     */
    @Test
    public void Test406() {
        String str = "cadoemajsdlkfjaadfvcses";
        long count = str.chars().filter(e->e=='a').count();
        System.out.println(count);

        int count2 = StringUtils.countMatches(str,'a');
        System.out.println(count2);

    }
}
