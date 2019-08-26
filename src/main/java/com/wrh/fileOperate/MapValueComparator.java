package com.wrh.fileOperate;

import java.util.Comparator;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:08 2019/6/15 0015
 * @Modified By:
 */
class MapValueComparator implements Comparator<Map.Entry<String, String>> {

    @Override
    public int compare(Map.Entry<String, String> me1, Map.Entry<String, String> me2) {

        return me2.getValue().compareTo(me1.getValue());
    }
}
