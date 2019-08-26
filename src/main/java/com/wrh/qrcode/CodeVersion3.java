package com.wrh.qrcode;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

import static com.wrh.qrcode.CodeParserFactory.convert;


/**
 * 码解析针对码Version V3.0
 *
 * @author lance
 * @since 2018.8.14 10:30
 */
public class CodeVersion3 extends AbstractCode {

    /**
     * V3.0版本解析
     *
     * @author lance
     * @since 2018.8.14 18:49
     */
    public static CodeInfo parseCode(String code) {
        //码头6位不加密
        String header = code.substring(0, 6);
        String version = header.substring(2, 3);
        String codeType = header.substring(0, 2);

        //码头后6位开始base64解码
        byte[] bytes = Base64.getUrlDecoder().decode(code.substring(6));
        //bytes前16位128位作为企业+产品+key+code
        int beforeLen = 16;

        //解析码16个byte, 即128Bit
        String data = byte2Binary(ArrayUtils.subarray(bytes, 0, beforeLen));

        int index = 0;
        long company = Long.parseLong(data.substring(index, 27), 2);
        String companyNo = (company + "").length() < 8 ?
                StringUtils.leftPad(company + "", 8, '0') : company + "";
        String belongIndustry = StringUtils.substring(companyNo, 0, 2);
        companyNo = Integer.valueOf(StringUtils.substring(companyNo, 2, 8)).toString();

        index += 27;

        String productNo = Long.parseLong(data.substring(index, index + 20), 2) + "";
        index += 20;

        String codeId = Long.parseLong(data.substring(index, index + 54), 2) + "";
        codeId = StringUtils.leftPad(codeId, 16, '0');
        index += 54;

        String key = Long.parseLong(data.substring(index, index + 14), 2) + "";
        key = key.length() < 4 ? StringUtils.leftPad(key, 4, '0') : key;
        index += 14;

        int len = Integer.parseInt(data.substring(index, index + 13), 2);
        int offset = 0;
        offset += 16;

        //解析离线数据
        String json = "";
        if (len > 0) {
            json = new String(Arrays.copyOfRange(bytes, offset, offset + len), Charset.forName("utf-8"));
            offset += len;
        }

        //解析码中CCKSID
        String ccksId = belongIndustry
                + StringUtils.leftPad(companyNo, 6, '0')
                + StringUtils.leftPad(key, 4, '0');

        //获取签名数据
        int signLen = bytes.length - offset;
        byte[] sign = new byte[signLen + 4];
        sign[0] = (byte) (((signLen + 4) >> 24) & 0xff);
        sign[1] = (byte) (((signLen + 4) >> 18) & 0xff);
        sign[2] = (byte) (((signLen + 4) >> 8) & 0xff);
        sign[3] = (byte) ((signLen + 4) & 0xff);
        System.arraycopy(bytes, offset, sign, 4, signLen);

        //拼装CodeInfo对象
        return CodeInfo.builder().header(header)
                .version(version)
                .companyNo(companyNo)
                .belongIndustry(belongIndustry)
                .productNo(productNo)
                .codeType(codeType(codeType))
                .codeId(codeId)
                .ccksId(ccksId)
                .data(json)
                .qrCode(code)
                .onlyCode(convert(companyNo, productNo, codeId, belongIndustry))
                .sign(sign)
                .build();
    }

    /**
     * byte数组转化为二进制字符串
     *
     * @param bytes 数据
     * @return 二进制字符串
     */
    private static String byte2Binary(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            String binaryString = Integer.toUnsignedString(b, 2);
            if (binaryString.length() > 8) {
                binaryString = binaryString.substring(binaryString.length() - 8, binaryString.length());
            } else if (binaryString.length() < 8) {
                binaryString = StringUtils.leftPad(binaryString, 8, '0');
            }

            builder.append(binaryString);
        }

        return builder.toString();
    }
}
