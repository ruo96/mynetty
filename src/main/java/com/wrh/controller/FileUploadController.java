package com.wrh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wuruohong
 * @Classname FileUploadController
 * @Description TODO
 * @Date 2020/12/8 12:02
 */
@Slf4j
@RestController
public class FileUploadController {

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    @RequestMapping("/file/upload")
    public Map<String, Object> upload(MultipartFile file, HttpServletRequest req){
        Map<String, Object> result = new HashMap<>();
        String originName = file.getOriginalFilename();
        if (!originName.endsWith(".pdf")) {
            result.put("status","error");
            result.put("msg","文件类型不对");
            return result;
        }
        /** 或者用fastdfs*/
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/") + format;
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String newName = UUID.randomUUID().toString() + ".pdf";
        log.info("存放服务器地址： {}", realPath + newName);
        log.info("folder： {}", folder.toString());
        log.info("getContextPath： {}", req.getServletContext().getContextPath());
        try {
            file.transferTo(new File(folder, newName));
            String url = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getServletContext().getContextPath()+format+newName;
            log.info("url： {}", url);
            result.put("status","success");
            result.put("url",url);
        } catch (IOException e) {
            e.printStackTrace();
            result.put("status","error");
            result.put("msg",e.getMessage());
        }
        return result;

    }
}
