package com.wrh.copy.utils;

import com.alibaba.fastjson.JSON;
import com.rits.cloning.Cloner;
import org.springframework.util.CollectionUtils;

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


}
