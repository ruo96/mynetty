package com.wrh.spring.custom.service;

import org.junit.Test;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wuruohong
 * @Classname MyMetaDataClassReader
 * @Description TODO
 * @Date 2022/2/22 18:48
 */
public class MyMetaDataClassReader {

    @Test
    public void Test12() throws IOException {

        /** 底层是asm  asm根据内部字节码的格式来解析 把class文件当做普通文件读取字节流*/
        SimpleMetadataReaderFactory simpleMetadataReaderFactory = new SimpleMetadataReaderFactory();
        MetadataReader metadataReader = simpleMetadataReaderFactory.getMetadataReader("com.wrh.spring.custom.service.User");

        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        System.out.println("classMetadata.getClassName() = " + classMetadata.getClassName());

        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        for (String annotationType : annotationMetadata.getAnnotationTypes()) {
            System.out.println(annotationType);
        }

        /** 这个是spring会用到的*/
        System.out.println(annotationMetadata.hasMetaAnnotation(Component.class.getName()));
        System.out.println(annotationMetadata.hasAnnotation(Component.class.getName()));

    }
}
