package com.wrh.utils;

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
}
