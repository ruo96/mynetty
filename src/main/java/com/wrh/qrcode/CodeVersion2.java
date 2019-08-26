package com.wrh.qrcode;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

import static com.wrh.qrcode.CodeParserFactory.convert;


/**
 * 码解析针对码Version V2.0
 *
 * @author lance
 * @since 2018.8.14 11:17
 */
public class CodeVersion2 extends AbstractCode {

    /**
     * 解析二维码
     *
     * @param code 码
     */
    public static synchronized CodeInfo parseCode(String code) {
        int offset = 0;
        byte[] bytes = Base64.getUrlDecoder().decode(code);
        if (bytes.length < MIN_LENGTH) {
            throw new RuntimeException("码格式不正常");
        }

        //获取6位码头信息
        String header = new String(bytes, 0, 6);
        if (!StringUtils.startsWithAny(header, HEADER_51, HEADER_52, HEADER_53)) {
            throw new RuntimeException("非法码");
        }

        //获取版本号和码类型
        String version = header.substring(2, 3);
        String codeType = header.substring(0, 2);

        //获取企业
        offset += 6;
        String companyNo = bcd2Str(Arrays.copyOfRange(bytes, offset, offset + 4), false);
        String belongIndustry = StringUtils.substring(companyNo, 0, 2);
        companyNo = Integer.valueOf(StringUtils.substring(companyNo, 2, 8)).toString();

        //获取产品编号
        offset += 4;
        String productNo = bcd2Str(Arrays.copyOfRange(bytes, offset, offset + 3), true);

        offset += 3;
        String codeId = bcd2Str(Arrays.copyOfRange(bytes, offset, offset + 8), false);
        codeId = StringUtils.leftPad(codeId, 16, '0');
        offset += 8;

        //解析码中附加信息
        short len = read(Arrays.copyOfRange(bytes, offset, offset + 2));
        offset += 2;
        String json = "";
        if (len > 0) {
            json = new String(Arrays.copyOfRange(bytes, offset, offset + len), Charset.forName("utf-8"));
            offset += len;
        }

        //解析码中CCKSID
        String ccksId = bcd2Str(Arrays.copyOfRange(bytes, offset, offset + 2), true);
        offset += 2;
        ccksId = belongIndustry + StringUtils.leftPad(companyNo, 6, '0') + StringUtils.leftPad(ccksId, 4, '0');

        //获取签名数据
        int signLen = bytes.length - offset;
        byte[] sign = new byte[signLen + 4];
        sign[0] = (byte) (((signLen + 4) >> 24) & 0xff);
        sign[1] = (byte) (((signLen + 4) >> 18) & 0xff);
        sign[2] = (byte) (((signLen + 4) >> 8) & 0xff);
        sign[3] = (byte) ((signLen + 4) & 0xff);
        System.arraycopy(bytes, offset, sign, 4, signLen);

        return CodeInfo.builder().header(header)
                .version(version)
                .companyNo(companyNo)
                .belongIndustry(belongIndustry)
                .productNo(productNo)
                .codeType(codeType(codeType))
                .codeId(codeId)
                .ccksId(ccksId)
                .qrCode(code)
                .onlyCode(convert(companyNo, productNo, codeId, belongIndustry))
                .data(json)
                .sign(sign)
                .build();
    }

    /**
     * 功能: BCD码转为10进制串(阿拉伯数据)
     * 参数: BCD码
     * 结果: 10进制串
     */
    private static String bcd2Str(byte[] bytes, boolean stripStart) {
        StringBuilder temp = new StringBuilder(bytes.length * 2);

        for (byte aByte : bytes) {
            temp.append((byte) ((aByte & 0xf0) >>> 4));
            temp.append((byte) (aByte & 0x0f));
        }

        if (!stripStart) {
            return temp.toString();
        }

        return StringUtils.stripStart(temp.toString(), "0");
    }

    /**
     * 大端数据转化为int类型
     *
     * @param bytes 数据
     * @return 数
     */
    private static short read(byte[] bytes) {
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            return stream.readShort();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
