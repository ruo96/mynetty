package com.wrh.copy.utils;

import com.alibaba.fastjson.JSON;
import com.rits.cloning.Cloner;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 7:21 2019/10/10 0010
 * @Modified By:
 */
public class DeepCopyUtils {
    private static final Cloner cloner = new Cloner();

    /**
     * 复制对象（深度拷贝）
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T clone(final T object){
        if (object == null) {
            return null;
        }
        return cloner.deepClone(object);
    }

    /**
     * 复制集合（深度拷贝）
     * @param object
     * @param <T>
     * @return
     */
    public static <T> List<T> cloneList(final List<T> object){
        if (object == null) {
            return null;
        }
        return cloner.deepClone(object);
    }

    /**
     * 复制对象到指定类（深度拷贝）
     * @param object
     * @param destclas 指定类
     * @param <T>
     * @return
     */
    public static <T> T clone(final Object object, Class<T> destclas){
        if (object == null) {
            return null;
        }
        String json = JSON.toJSONString(object);
        return JSON.parseObject(json, destclas);
    }

    /**
     * 复制集合到指定类（深度拷贝）
     * @param object
     * @param destclas 指定类
     * @param <T>
     * @return
     */
    public static <T> List<T> cloneList(List<?> object, Class<T> destclas) {
        if (object == null) {
            return new ArrayList<T>();
        }
        String json = JSON.toJSONString(object);
        return JSON.parseArray(json, destclas);
    }

    /**
     * 和上面的方式是一样的
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List copyList(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        }
        return JSON.parseArray(JSON.toJSONString(list), list.get(0).getClass());
    }

    /**
     *
     * @param map
     * @return
     */
    public static Map<String, Object> copyMap(Map map) {
        return JSON.parseObject(JSON.toJSONString(map));
    }



    public static <T> T deepClone(T obj) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();

        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T obj)
            throws Exception {
        //在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组中
        //默认创建一个大小为32的缓冲区
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        //对象的序列化输出
        ObjectOutputStream oos = new ObjectOutputStream(bout); //通过字节数组的方式进行传输
        oos.writeObject(obj); //将当前student对象写入字节数组中

        //在内存中创建一个字节数组缓冲区，从输入流读取的数据保存在该字节数组缓冲区
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray()); //接收字节数组作为参数进行创建
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject(); //从字节数组中读取

        // 说明：调用ByteArrayInputStream
        //或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，
        //这一点不同于对外部资源（如文件流）的释放
    }


}
