package com.wrh.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * @author wuruohong
 * @Classname FileUploadController
 * @Description TODO
 * @Date 2020/12/8 12:02
 */
@Slf4j
@RestController
public class FileReadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReadController.class);
    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    @Value("classpath:data.txt")  // 重要，必须用spring的这个注解才行
    Resource resource;

    /**
     * 但是这种方式不适用于读取 jar 包中的文件，鉴于现在很多应用都是通过 FatJar 方式部署，我们还需要找找其他方式
     */
    @RequestMapping("/file/read")
    public String read(HttpServletRequest req) throws IOException {
        System.out.println("ready to read");
        File file = resource.getFile();
        String content = new String(Files.readAllBytes(file.toPath()));
        System.out.println("content = " + content);

        return content;
    }

    @RequestMapping("/file/read/stream")
    public String readStream(HttpServletRequest req) throws IOException {
        System.out.println("ready to read stream");
        LOGGER.info(" read file！ {}", LocalDateTime.now().toString());
        InputStream resource = new ClassPathResource("data.txt").getInputStream();
        String content="";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
            content = reader.lines().collect(Collectors.joining(" "));
        }

        return content;
    }
}
