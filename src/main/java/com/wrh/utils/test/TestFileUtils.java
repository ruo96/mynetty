package com.wrh.utils.test;

import com.wrh.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author wuruohong
 * @Classname TestFileUtils
 * @Description TODO
 * @Date 2020/3/4 19:08
 */
@Slf4j
public class TestFileUtils {

    @Test
    public void Test() throws IOException {
        String path = "e:\\file\\test.txt";
        FileUtils.writeString("dasheng",path);
        String path1 = "e:\\file\\test1.txt";
        FileUtils.writeString("dasheng",path1);
    }

    @Test
    public void Test27() throws IOException, NoSuchAlgorithmException {
        String filePath1 = "E:\\file\\data.txt";
        String filePath2 = "E:\\file\\data1.txt";
        String result1 = FileUtils.extrackChecksum(filePath1, "MD5");
        String result2 = FileUtils.extrackChecksum(filePath2, "MD5");
        Assert.assertEquals(result1, result2);


    }
}
