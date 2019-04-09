package com.wrh.fileOperate;

import java.io.File;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:46 2019/4/4 0004
 * @Modified By:
 */
public class TestFileOperate {
    public static void main(String[] args) {
        String path = (new File("/home/zcs/public")).getAbsolutePath();
        System.out.println(path);
    }
}
