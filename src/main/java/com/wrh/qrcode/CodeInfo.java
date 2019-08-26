package com.wrh.qrcode;

import lombok.Builder;
import lombok.Data;

/**
 * Created By @author Zing @date 2018/8/17 14:24
 */
@Data
@Builder
public class CodeInfo {
    /**
     * 产品码
     */
    public static final String PRODUCT_CODE = "1";
    /**
     * 包装码
     */
    public static final String PACKAGE_CODE = "2";
    /**
     * 流通码
     */
    public static final String PROCESS_CODE = "3";

    /**
     * 码头
     */
    private String header;
    /**
     * 版本
     */
    private String version;
    /**
     * 企业ID
     */
    private String companyNo;
    /**
     * 产品ID
     */
    private String productNo;
    /**
     * 码ID
     */
    private String codeId;
    /**
     * 码类型
     * 1:产品码
     * 2:包装码
     * 3:流通码
     */
    private String codeType;
    /**
     * CCKS ID
     */
    private String ccksId;
    /**
     * 唯一编码
     */
    private String onlyCode;
    /**
     * 签名
     */
    private byte[] sign;

    /**
     * 附加参数
     */
    private String data;

    /**
     * 所属行业
     */
    private String belongIndustry;

    /**
     * 码字符串
     */
    private String qrCode;

    public CodeInfo() {
    }

    public CodeInfo(String header, String version, String companyNo, String productNo, String codeId,
                    String codeType, String ccksId, String onlyCode, byte[] sign, String data,
                    String belongIndustry, String qrCode) {
        this.header = header;
        this.version = version;
        this.companyNo = companyNo;
        this.productNo = productNo;
        this.codeId = codeId;
        this.codeType = codeType;
        this.ccksId = ccksId;
        this.onlyCode = onlyCode;
        this.sign = sign;
        this.data = data;
        this.belongIndustry = belongIndustry;
        this.qrCode = qrCode;
    }
}
