package com.wrh.controller.multidb;

public class DataSourceContextHolder {
    private static InheritableThreadLocal<String> dbKey = new InheritableThreadLocal<>();

    public static void setDbKey(String key){
        dbKey.set(key);
    }

    public static String getDbKey(){
        return dbKey.get();
    }
}
