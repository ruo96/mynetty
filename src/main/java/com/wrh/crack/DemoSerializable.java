package com.wrh.crack;

import org.springframework.scheduling.annotation.AsyncResult;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author wuruohong
 * @Classname DemoSerializable
 * @Description TODO
 * @Date 2021/10/20 18:02
 */
public class DemoSerializable {
    public static void main(String[] args) throws Exception {
        //定义myObj对象
        MyObject myObj = new MyObject();
        myObj.name = "hello world";
        //创建一个包含对象进行反序列化信息的”object”数据文件
        FileOutputStream fos = new FileOutputStream("object");
        ObjectOutputStream os = new ObjectOutputStream(fos);
        //writeObject()方法将myObj对象写入object文件
        os.writeObject(myObj);
        os.close();
        //从文件中反序列化obj对象
        FileInputStream fis = new FileInputStream("object");
        ObjectInputStream ois = new ObjectInputStream(fis);
        //恢复对象
        MyObject objectFromDisk = (MyObject)ois.readObject();  // 就是这里产生了大的漏洞
        System.out.println(objectFromDisk.name);
        ois.close();

    }
}
