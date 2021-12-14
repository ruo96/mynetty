package com.wrh.utils;

import java.text.DecimalFormat;

/**
 * @author wuruohong
 * @Classname FormatUtils
 * @Description TODO
 * @Date 2021/12/6 11:14
 */
public class FormatUtils {
    /**
     * 单位转换
     *
     * @param byteNumber number
     * @return desc
     */
    public static String formatByte(long byteNumber) {
        //换算单位
        double FORMAT = 1024.0;
        double kbNumber = byteNumber / FORMAT;
        if (kbNumber < FORMAT) {
            return new DecimalFormat("#.##KB").format(kbNumber);
        }
        double mbNumber = kbNumber / FORMAT;
        if (mbNumber < FORMAT) {
            return new DecimalFormat("#.##MB").format(mbNumber);
        }
        double gbNumber = mbNumber / FORMAT;
        if (gbNumber < FORMAT) {
            return new DecimalFormat("#.##GB").format(gbNumber);
        }
        double tbNumber = gbNumber / FORMAT;
        return new DecimalFormat("#.##TB").format(tbNumber);
    }
}
