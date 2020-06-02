package com.wrh.cache.caffeine;

import lombok.Data;

/**
 * @author wuruohong
 * @Classname DataObject
 * @Description TODO
 * @Date 2020/6/1 18:50
 */
@Data
public class DataObject {

    private final String data;

    private static int objectCounter = 0;

    public DataObject(String data) {
        this.data = data;
    }
    // standard constructors/getters

    public static DataObject get(String data) {
        objectCounter++;
        return new DataObject(data);
    }


}
