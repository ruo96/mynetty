package com.wrh.serialize;

import java.io.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:15 2019/5/17 0017
 * @Modified By:
 */
public class TestSerialize {
    /**
     * 将User对象作为文本写入磁盘
     */
    public static void writeObj() {
        User user = new User("1001", "Joe");
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("/home/user.txt"));
            objectOutputStream.writeObject(user);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将类从文本中提取并赋值给内存中的类
     */
    public static void readObj() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("/home/user.txt"));
            try {
                Object object = objectInputStream.readObject();
                User user = (User) object;
                System.out.println(user.toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        //序列化和反序列化的时候，使用的对象里面的序列化id必须一致才行，所以那个值只要不变化， 用什么都可以的
        writeObj();
        readObj();
    }
}
