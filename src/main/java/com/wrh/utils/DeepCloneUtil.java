package com.wrh.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.wrh.elasticsearch.Student;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author wuruohong
 * @Classname DeepCloneUtil
 * @Description TODO
 * @Date 2020/12/18 16:44
 */
public class DeepCloneUtil {
    private static Object deepclone(Object obj) throws IOException, ClassNotFoundException {
        Object cloneObj=null;
        //在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组中
        //默认创建一个大小为32的缓冲区
        ByteArrayOutputStream byOut=new ByteArrayOutputStream();
        //对象的序列化输出
        ObjectOutputStream outputStream=new ObjectOutputStream(byOut);//通过字节数组的方式进行传输
        outputStream.writeObject(obj);  //将当前student对象写入字节数组中

        //在内存中创建一个字节数组缓冲区，从输入流读取的数据保存在该字节数组缓冲区
        ByteArrayInputStream byIn=new ByteArrayInputStream(byOut.toByteArray()); //接收字节数组作为参数进行创建
        ObjectInputStream inputStream=new ObjectInputStream(byIn);
        cloneObj= inputStream.readObject(); //从字节数组中读取
        return  cloneObj;
    }

    /**
     *  Apache Commons Lang序列化
     * Java提供了序列化的能力，我们可以先将源对象进行序列化，再反序列化生成拷贝对象。
     * 但是，使用序列化的前提是拷贝的类（包括其成员变量）需要实现Serializable接口。
     * Apache Commons Lang包对Java序列化进行了封装，我们可以直接使用它。
     */
    private static Object deepcloneByCommonApache(Student obj) throws IOException, ClassNotFoundException {
        Object cloneObj=null;
        cloneObj = SerializationUtils.clone(obj);
        return  cloneObj;
    }

    /**
     * Gson序列化
     * Gson可以将对象序列化成JSON，也可以将JSON反序列化成对象，所以我们可以用它进行深拷贝。
     */
    private static Object deepcloneByGson(Object obj) throws IOException, ClassNotFoundException {
        Object cloneObj=null;

        Gson gson = new Gson();
        cloneObj = gson.fromJson(gson.toJson(obj),obj.getClass());

        return  cloneObj;
    }

    /**
     * jackson与Gson相似，可以将对象序列化成JSON，明显不同的地方是拷贝的类（包括其成员变量）需要有默认的无参构造函数。
     */
    private static Object deepcloneByJackson(Object obj) throws IOException, ClassNotFoundException {
        Object cloneObj=null;

        ObjectMapper objectMapper = new ObjectMapper();
        cloneObj = objectMapper.readValue(objectMapper.writeValueAsString(obj), obj.getClass());

        return  cloneObj;
    }

    @Test
    public void Test38() throws IOException, ClassNotFoundException {
        Student s = new Student();
        s.setName("w1");
        s.setId(1);

        Student s1 = (Student) deepcloneByCommonApache(s);
        s1.setName("w2");

        Student s2 = (Student) deepcloneByGson(s);
        s2.setName("w3");

        Student s3 = (Student) deepcloneByJackson(s);
        s3.setName("w4");

        System.out.println("s = " + s);
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println("s3 = " + s3);


    }


}
