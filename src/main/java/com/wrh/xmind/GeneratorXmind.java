package com.wrh.xmind;

import cn.hutool.core.util.ReUtil;
import com.google.common.collect.Lists;
import jodd.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.xmind.core.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:10 2019/12/5 0005
 * @Modified By:
 */
@Slf4j
public class GeneratorXmind {

//    public static final String CLASS_PATH = GeneratorXmind.class.getResource("/").getPath();
    public static final String CLASS_PATH = "e:\\file\\";
    public static final String FILE_SEPARATOR = File.separator;
//    public static final String FILE_SEPARATOR = SystemUtil.getOsInfo().getFileSeparator();

    public static void main(String[] args) throws IOException, CoreException {

        log.info("===> path: {} separator: {}", CLASS_PATH, FILE_SEPARATOR);
        String bookName = "content";

        List<String> contents = Arrays.asList(FileUtil.readLines(CLASS_PATH + bookName + ".txt"));

        IWorkbookBuilder workbookBuilder = Core.getWorkbookBuilder();
        IWorkbook workbook = workbookBuilder.createWorkbook();

        ISheet primarySheet = workbook.getPrimarySheet();

        ITopic rootTopic = primarySheet.getRootTopic();

        rootTopic.setTitleText(bookName);

        ArrayList<ITopic> chapterTopics = Lists.newArrayList();
        for (String content: contents ) {
            if(ReUtil.isMatch("^[1-24].*?",content)) {
                ITopic topic = workbook.createTopic();
                topic.setTitleText(content);
                chapterTopics.add(topic);
            } else {
                ITopic topic = workbook.createTopic();
                topic.setTitleText(content);
                chapterTopics.get(chapterTopics.size() - 1).add(topic,ITopic.ATTACHED);
            }
        }

        chapterTopics.forEach(it -> rootTopic.add(it, ITopic.ATTACHED));

        workbook.save(CLASS_PATH + FILE_SEPARATOR + bookName + ".xmind");
    }
}
