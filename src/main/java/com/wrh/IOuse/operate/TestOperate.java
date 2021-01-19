package com.wrh.IOuse.operate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

/**
 * @author wuruohong
 * @Classname TestOperate
 * @Description TODO
 * @Date 2021/1/18 10:55
 */
@Slf4j
public class TestOperate {

    @Test
    public void Test15() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(URI.create("https://www.baidu.com"));
    }
}
