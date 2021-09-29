package com.wrh.collection.map;

import java.util.Comparator;

/**
 * @author wuruohong
 * @Classname MapKeyComparator
 * @Description TODO
 * @Date 2021/6/24 17:04
 */
public class MapKeyComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
