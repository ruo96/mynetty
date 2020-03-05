package com.wrh.utils.test;

import com.wrh.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

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
}
