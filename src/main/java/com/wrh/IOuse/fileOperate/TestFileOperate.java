package com.wrh.IOuse.fileOperate;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
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
        Set<String> set = new HashSet<>();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
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
     * 删除字符串最后一个字符的多种方法， 最后一位
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

    @Test
    public void Test420() throws IOException {
        File f = new File("e:\\file\\test.txt");
        FileInputStream fileInputStream = new FileInputStream(f);

        int b ;
        while((b = fileInputStream.read()) != -1){
            System.out.println((char)b);
        }

    }

    /**
     * 写文件的6种写法  FileWriter  BufferedWriter  PrintWriter FileOutputStream BufferedOutputStream
     * 字符流的操作速度最快，这是因为我们本次测试的代码操作的是字符串，所以在使用字节流时，需要先将字符串转换为字节流，因此在执行效率上不占优势。
     * 性能最好的是带有缓冲区的字符串写入流 BufferedWriter，性能最慢的是 Files。
     */
    @Test
    public void Test432() throws IOException {
        fileWriterMethod("e:\\file\\test.txt","测试用");
    }

    /**
     * 方法 1：使用 FileWriter 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    public static void fileWriterMethod(String filepath, String content) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filepath)) {
            fileWriter.append(content);
        }

        /** 追加格式*/
        try (FileWriter fileWriter = new FileWriter(filepath, true)) {
            fileWriter.append(content);
        }
    }

    /**
     * 方法 2：使用 BufferedWriter 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    public static void bufferedWriterMethod(String filepath, String content) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))) {
            bufferedWriter.write(content);
        }

        /** 追加格式*/
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath,true))) {
            bufferedWriter.write(content);
        }
    }

    /**
     * 方法 3：使用 PrintWriter 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    public static void printWriterMethod(String filepath, String content) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filepath))) {
            printWriter.print(content);
        }

        /** 追加格式*/
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filepath,true))) {
            printWriter.print(content);
        }
    }

    /**
     * 方法 4：使用 FileOutputStream 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    public static void fileOutputStreamMethod(String filepath, String content) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filepath)) {
            byte[] bytes = content.getBytes();
            fileOutputStream.write(bytes);
        }

        /** 追加*/
        try (FileOutputStream fileOutputStream = new FileOutputStream(filepath,true)) {
            byte[] bytes = content.getBytes();
            fileOutputStream.write(bytes);
        }
    }

    /**
     * 方法 5：使用 BufferedOutputStream 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    public static void bufferedOutputStreamMethod(String filepath, String content) throws IOException {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream(filepath))) {
            bufferedOutputStream.write(content.getBytes());
        }

        /** 追加*/
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream(filepath, true))) {
            bufferedOutputStream.write(content.getBytes());
        }
    }

    /**
     * 方法 6：使用 Files 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    public static void filesTest(String filepath, String content) throws IOException {
        Files.write(Paths.get(filepath), content.getBytes());

        /** 追加*/
        Files.write(Paths.get(filepath), content.getBytes(), StandardOpenOption.APPEND);
    }

    @Test
    public void Test544() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8080));
        String fileName = "";
        FileChannel channel = new FileInputStream(fileName).getChannel();
        channel.transferTo(0, channel.size(), socketChannel);

    }

    /**
     * sku 数据很多数据，无法加载到内存。  流式读取文件
     *
     * 也就是说我们不能将数据全部读到内存中，然后遍历打印。
     *
     * 这就要求我们只能通过「流的方式」，逐条读取，然后打印输出。
     *
     * 流的方式，可以想到使用 BufferedReader 方式，一行一行读取。
     *
     * 不过，使用BufferedReader相关代码比较繁琐，由于当前笔试没要求 JDK 版本的，所以我们可以通过使用 JDK8  Files#lines 的流式读取的方式。
     * @throws IOException
     */
    @Test
    public void Test558() throws IOException {
        Files.lines(ResourceUtils.getFile("e:\\1.txt").toPath())
                .skip(1)
                .forEach(line->{
                    System.out.println("["+line+"]");
                });

        File file = new File("e:\\1.txt");

    }

    @Test
    public void Test584() throws IOException {
        // 价格为 key，value 为 sku 出现的次数
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
// 使用题目一的方法遍历读取文件，将数据加载到  treeMap 中
        Files.lines(ResourceUtils.getFile("e:\\1.txt").toPath())
                // 跳过标题头
                /*.skip(1)*/
                // 遍历元素
                .forEach(line -> {
                    // 将一行的字符串转化为一个对象，然后打印输出
                    // 由于价格都是整数
                    // 这里使用 merge，如果当前这个价格在 map 中不存在，则值为 1，否则将调用后面的设置的函数
                    treeMap.merge(Integer.valueOf(line), 1, Integer::sum);
                });
        System.out.println(treeMap);
        System.out.println("treeMap.size() = " + treeMap.size());
        int mid = treeMap.size() / 2;
        int midPrice = 0;
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            // 第一次 count 大于等于 mid，代表中位数位于这个区间
            if (count + entry.getValue() >= mid) {
                // 中间的价格,偶数的情况下随便选一个
                midPrice = entry.getKey();
                break;
            }
            count += entry.getValue();

        }
        System.out.println("价格排序后在中间的价格为" + midPrice);

    }

    @Test
    public void Test615() throws IOException {
        File file = new File("a.jpg");
        file.createNewFile();
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("file.getPath() = " + file.getPath());

    }

    @Test
    public void Test624() throws IOException {
        FileWriter fileWriter = new FileWriter("d:\\test.txt");
        fileWriter.write(97);
        fileWriter.close();

    }

    @Test
    public void Test635() throws IOException {
        List<String> list = Files.readAllLines(Paths.get("e:\\file\\data.txt"));
        System.out.println("list = " + list);

    }

    @Test
    public void Test632Daletou() throws IOException {

        List<Integer> beforeResult = new ArrayList<>(5);
        List<Integer> afterResult = new ArrayList<>(2);
        List<String> buyList = new ArrayList<>(4);
        for (int i = 0; i < 1; i++) {
            while (beforeResult.size() != 5) {
                Integer beforeNum = RandomUtils.nextInt(1,36);
                if (!beforeResult.contains(beforeNum)) {
                    beforeResult.add(beforeNum);
                }
            }
            while (afterResult.size() != 2) {
                Integer afterNum = RandomUtils.nextInt(1,12);
                if (!afterResult.contains(afterNum)) {
                    afterResult.add(afterNum);
                }
            }
            beforeResult.sort(Integer::compareTo);
            afterResult.sort(Integer::compareTo);
            String result = JSON.toJSONString(beforeResult) + " + " + JSON.toJSONString(afterResult);
            buyList.add(result);
            beforeResult.clear();
            afterResult.clear();
        }
        File file = new File("dlt.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write("\r\n");
        fileWriter.write("\r\n");
        fileWriter.write(LocalDateTime.now().toString());
        fileWriter.write("\r\n");
        buyList.stream().forEach(e-> {
            try {
                fileWriter.write(e);
                fileWriter.write("\r\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        try {
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void Test689() throws URISyntaxException {
//        URL location = TestFileOperate.class.getClassLoader().getResource("test.txt");
//        D:\pro\mynetty\src\main\java\com\wrh\IOuse\fileOperate\test.txt
        System.out.println("TestFileOperate.class.getResource(\"\") = " + TestFileOperate.class.getResource(""));
        System.out.println("TestFileOperate.class.getResource(\"\") = " + TestFileOperate.class.getResource("/"));
        System.out.println("TestFileOperate.class.getClassLoader.getResource(\"\") = " + TestFileOperate.class.getClassLoader().getResource(""));
        System.out.println("TestFileOperate.class.getClassLoader.getResource(\"\") = " + TestFileOperate.class.getClassLoader().getResource("/"));
        URL location = TestFileOperate.class.getResource("test.txt");
        System.out.println(location);
        Path path = Paths.get(location.toURI());
        System.out.println(location.toURI());

    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        URL location = TestFileOperate.class.getClassLoader().getResource("test.txt");
        System.out.println(location.toString());
        Path path = Paths.get(location.toURI());
        System.out.println(location.toURI());

        List<String> content = Files.lines(path).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("content = " + content);
    }

    /** inputStream转string  读取文件的几种方法*/
    @Test
    public void Test718() {
        try {
            InputStream inputStream = new FileInputStream("E:/file/data.txt");    //路径修改为本地文件所在的位置
            String myString =  getStringBuilder6(inputStream);
            System.out.println("myString = " + myString);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /** 1、使用 InputStreamReader 和 StringBuilder (JDK)*/
    private String getStringBuilder(InputStream inputStream) throws IOException {
        char[] buffer = new char[1024];    //根据需要的数组大小进行自定义
        StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "UTF-8");
        for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0; ) {
            out.append(buffer, 0, numRead);
        }
        return out.toString();
    }

    /** 2、使用 inputStream.read() and StringBuilder*/
    private String getStringBuilder2(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int ch; (ch = inputStream.read()) != -1; ) {
            sb.append((char) ch);
        }
        return sb.toString();
    }

    /** 3、使用 ByteArrayOutputStream and inputStream.read*/
    private String getStringBuilder3(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int length; (length = inputStream.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }

    /** 4、使用 BufferedInputStream 和 ByteArrayOutputStream*/
    private String getStringBuilder4(InputStream inputStream) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        for (int result = bis.read(); result != -1; result = bis.read()) {
            buf.write((byte) result);
        }
        return buf.toString("UTF-8");
    }

    /** 5、使用 BufferedReader*/
    private String getStringBuilder5(InputStream inputStream) throws IOException {
        String newLine = System.getProperty("line.separator");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        for (String line; (line = reader.readLine()) != null; ) {
            if (result.length() > 0) {
                result.append(newLine);
            }
            result.append(line);
        }
        return result.toString();
    }

    /** 6、使用 Stream API 或 parallel Stream API*/
    private String getStringBuilder6(InputStream inputStream) throws IOException {
        /*String myString = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));*/

        String myString1 = new BufferedReader(new InputStreamReader(inputStream))
                .lines().parallel().collect(Collectors.joining("\n"));
        return myString1;
    }

    /** 7、使用 StringWriter 和IOUtils.copy (Apache Commons)*/
    private String getStringBuilder7(InputStream inputStream) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF-8");
        return writer.toString();


        /*String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        return result;*/
    }

    /** 8、使用CharStreams (Google Guava)*/
    private String getStringBuilder8(InputStream inputStream) throws IOException {
        String result = CharStreams.toString(new InputStreamReader(
                inputStream, Charsets.UTF_8));
        return result;
    }

    /**
     * 样板代码  读取文件
     * @throws Exception
     */
    @Test
    public void Test819() throws Exception {
        String fileName = "e:/file/data.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while (Objects.nonNull(line = reader.readLine())) {
                System.out.println("line = " + line);
            }
        } catch (IOException e) {
            String message = String.format("读取文件(%s)异常", fileName);
            log.error(message, e);
            throw new Exception(message, e);
        }
        // 可以优化为下面方法

    }
    @Test
    public void Test839() throws Exception {
        String fileName = "e:/file/data.txt";
        readLine(fileName, line->{
            System.out.println("line = " + line);
        });

    }
    public static void readLine(String fileName, Consumer<String> lineConsumer) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while (Objects.nonNull(line = reader.readLine())) {
                lineConsumer.accept(line);
            }
        } catch (IOException e) {
            String message = String.format("读取文件(%s)异常", fileName);
            log.error(message, e);
            throw new Exception(message, e);
        }
    }

    @Test
    public void Test858() throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("E:\\file\\data.txt","rw");
        FileChannel fileChannel = accessFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int readCount = fileChannel.read(buffer);

        System.out.println("readCount = " + readCount);
        System.out.println("new String(buffer.array()) = " + new String(buffer.array()));

        ByteBuffer buffer1 = ByteBuffer.allocate(1024);
        String content = "\r\n这是写入的一段话";
        buffer1.put(content.getBytes("UTF-8"));
        buffer1.flip();
        fileChannel.write(buffer1);
        fileChannel.close();


    }
}
