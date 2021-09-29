package com.wrh.collection.map;

import java.util.Comparator;

/**
 * @author wuruohong
 * @Classname MyTreeMapComparator
 * @Description TODO
 * @Date 2021/6/2 17:34
 */
public class MyTreeMapComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        String i1 = (String) o1;
        String i2 = (String) o2;
        return -i1.compareTo(i2);
    }
}
