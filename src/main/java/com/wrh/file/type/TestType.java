package com.wrh.file.type;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author wuruohong
 * @Classname TestType
 * @Description TODO
 * @Date 2021/2/5 16:24
 */
@Slf4j
public class TestType {
    @Test
    public void Test14() throws IOException {
        Path path = new File("e:\\maoshu.jpg").toPath();
        String mimeType = Files.probeContentType(path);
        System.out.println("mimeType = " + mimeType);

    }
}
