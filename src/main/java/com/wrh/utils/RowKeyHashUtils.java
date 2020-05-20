/*

 */
package com.wrh.utils;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * 类RowKeyHashUtils.java的实现描述：
 *
 * @author wujian01 2019年12月10日 上午11:51:07
 */
public class RowKeyHashUtils {

    /** ROWKEY hash后的分隔符 **/
    public final static String ROW_KEY_HASH_CODE = "@|";

    /**
     ** 根据rowKey进行散列
     *
     * @param strRowKey
     * @return
     */
    public static byte[] obtainHashRowKeyByUniformSplit(String strRowKey) {
        byte[] rowkey = Bytes.toBytes(obtainHashRowKeyStr(strRowKey));
        return rowkey;
    }



    /**
     ** 追加散列前缀(prifiex + rowKey)
     *
     * @param strRowKey
     * @return
     */
    public static String obtainHashRowKeyStr(String strRowKey) {
        //有hash分隔符，则直接返回
        if (strRowKey.indexOf(ROW_KEY_HASH_CODE) != -1) {
            return strRowKey;
        }
        String result = "";
        String md5Hash = Md5Utils.encrypt(strRowKey);
        String sub5Hash = md5Hash.substring(0, 5);
        result = sub5Hash.concat(ROW_KEY_HASH_CODE).concat(strRowKey);
        return result;
    }


    /**
     ** 将rowKey按MD5完全哈希，保留16位
     *
     * @param strRowKey
     * @return
     */
    public static String hashRowKeyStr(String strRowKey) {
        String result = "";
        String md5Hash = Md5Utils.encrypt(strRowKey);
        result = md5Hash;
        return result;
    }

    /**
     ** 判断是否是hash的key
     *
     * @param strRowKey
     * @return boolean
     */
    public static boolean isHashKey(String strRowKey){
        return strRowKey.indexOf(ROW_KEY_HASH_CODE)!=-1;
    }

    /**
     ** 将已hash的key转成原始key
     *
     * @param strRowKey
     * @return String
     */
    public static String getOrigionKey(String strRowKey){
        if(!isHashKey(strRowKey)){
            return strRowKey;
        }

        return strRowKey.substring(strRowKey.indexOf(ROW_KEY_HASH_CODE) + ROW_KEY_HASH_CODE.length());
    }

}
