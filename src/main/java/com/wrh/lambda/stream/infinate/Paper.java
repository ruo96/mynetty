package com.wrh.lambda.stream.infinate;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wuruohong
 * @Classname Paper
 * @Description TODO
 * @Date 2020/6/2 19:27
 */
@Data
@AllArgsConstructor
public class Paper {
    private String studentName;

    private String className;

    private int score;
}
