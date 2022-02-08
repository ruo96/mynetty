package com.wrh.collection.map;

import lombok.Data;

/**
 * @author wuruohong
 * @Classname MyServer
 * @Description TODO
 * @Date 2022/2/7 14:31
 */
@Data
public class MyServer {
    private String name;

    public MyServer(String name){
        this.name = name;
    }
}
