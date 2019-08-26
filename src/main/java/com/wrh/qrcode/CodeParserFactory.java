package com.wrh.qrcode;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 码解析工具类
 *
 * @author lance
 * @since 2018.8.14 10:39
 */
public final class CodeParserFactory {

    /**
     * 二维码码标记
     */
    private static final String MID_FLG = "?q=";
    private static final String SLASH_FLG = "/";
    private static final int DEFAULT_CODE_LEN = 16;
    private static AtomicInteger atomic = new AtomicInteger(1);

    private CodeParserFactory() {
    }

    /**
     * 解析二维码
     *
     * @param code 码
     * @return 信息
     */
    public static synchronized CodeInfo parseCode(String code) {
        //老码以http://c.zcsmart.com/code?q=xxx
        if (StringUtils.contains(code, MID_FLG)) {
            code = StringUtils.substringAfter(code, MID_FLG);
        }

        //新码以http://c.zcsmart.com/xxx
        if (StringUtils.contains(code, SLASH_FLG)) {
            code = StringUtils.substringAfterLast(code, SLASH_FLG);
        }

        String header = code.substring(0, 6);
        if (!NumberUtils.isDigits(header)) {
            return CodeVersion2.parseCode(code);
        }

        //新码转换
        String version = header.substring(2, 3);
        switch (version) {
            case "3":
                return CodeVersion3.parseCode(code);
            default:
                return null;
        }
    }

    /**
     * 该方法是中城默认标准。 生产码串30位,[企业编号: 8,产品编号: 6, 码编号: 16]
     *
     * @param companyId 企业编号
     * @param productId 产品编号
     * @param codeId    码编号
     * @return 唯一编码
     */
    public static String convert(String companyId, String productId, String codeId) {
        return convert(companyId, productId, codeId, "00");
    }

    /**
     * 生成指定长度随机数字
     *
     * @param length 指定长度
     * @return 返回指定长度随机数
     */
    public static synchronized String random(int length) {
        if (length == DEFAULT_CODE_LEN) {
            return String.format("%04d", atomic.getAndIncrement() % 10000) + DateFormatUtils.format(System.currentTimeMillis(), "yyMMddHHmmss");
        }
        return RandomStringUtils.randomNumeric(length);
    }

    /**
     * 根据码类型, 转换码的长度
     * <table>
     * <tr>
     * <td>00</td>
     * <td>中城标准</td>
     * </tr>
     * <tr>
     * <td>01</td>
     * <td>盐业标准</td>
     * </tr>
     * </table>
     *
     * @param companyId      企业编号
     * @param productId      产品编号
     * @param codeId         码编号
     * @param belongIndustry 行业标准
     * @return 唯一编码
     */
    public static String convert(String companyId, String productId, String codeId, String belongIndustry) {
        switch (belongIndustry) {
            case "01":
                companyId = StringUtils.leftPad(companyId, 4, '0');
                companyId = StringUtils.substring(companyId, -4);

                productId = StringUtils.leftPad(productId, 4, '0');
                productId = StringUtils.substring(productId, -4);
                break;


            default:
                companyId = String.join("", StringUtils.leftPad(companyId, 8, '0'));
                productId = StringUtils.leftPad(productId, 6, '0');
                break;
        }

        return String.join("", companyId, productId, codeId);
    }
}
