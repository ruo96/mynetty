package com.wrh.assert1;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:06 2018/11/29 0029
 * @Modified By:
 */
public class TestAssert {
    public static void main(String[] args) {
        /*String i = "123";
        i = null;
        Assert.notNull(i,"不能为空");*/


        String a = "/home/pack/123.zip";
        System.out.println(a.substring(a.lastIndexOf("/")+1));

        String b = "3";
        System.out.println("==="+Integer.valueOf(b));

        String  path = "/ftp/data/csc/1/auto-1-112900000120190417006.zip";
        int num = path.lastIndexOf("/");
        System.out.println("num: " + num);
        System.out.println("path: " + path.substring(0,num));  //path.substring(0,index),path.substring(index+1)
        System.out.println("file: " + path.substring(num+1));  //path.substring(0,index),path.substring(index+1)
        System.out.println("replace: " + path.substring(0,num).replace("/ftp/data/csc/",""));
        String newPath = path.substring(0,num);
        System.out.println("newPath: " + newPath.substring(newPath.lastIndexOf("/") + 1));
    }

    @Test
    public void test(){
        int i1 = 100;
        int i2 = 100;
        assert i1 != i2;
    }
    
    @Test
    public void Test1() {
        String i = null;
        Assert.notNull(i,"i 为空");
    }
}
