package com.wrh.lambda.stream.learnIterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:45 2018/8/30 0030
 * @Modified By:
 */
public class TestLearnIterator {

    private static void learnIterator(){
        List<String> list = Arrays.asList("A","B","C","D");

        Iterator<String> iterator = list.stream().iterator();
    }
}
