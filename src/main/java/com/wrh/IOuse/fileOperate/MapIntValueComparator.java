package com.wrh.IOuse.fileOperate;

import java.util.Comparator;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:08 2019/6/15 0015
 * @Modified By:
 */
class MapIntValueComparator implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

        return me2.getValue().compareTo(me1.getValue());
    }
}
