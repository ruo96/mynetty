package com.wrh.lambda.stream.vo;

import lombok.Data;

import java.util.Objects;
import java.util.Optional;

/**
 * 卷内信息
 * Created By @author Zing @date 2018/9/18 9:41
 */
@Data
public class BundleInfo implements Comparable<Object> {
    /**
     * 序号
     */
    private Integer serialNo;
    /**
     * 二维码
     */
    private String qrcode;


    /**
     * 所属的大卷码号
     */
    private String largeBundleNumber;




    /**
     * 遗漏的码参考码信息
     */
    private Optional<BundleInfo> makeupSource;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BundleInfo that = (BundleInfo) o;
        return Objects.equals(serialNo, that.serialNo)
                && Objects.equals(qrcode, that.qrcode)
                && Objects.equals(largeBundleNumber, that.largeBundleNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNo, qrcode);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof BundleInfo) {
            BundleInfo other = (BundleInfo) o;

            int ret = this.largeBundleNumber.compareTo(other.largeBundleNumber);
            if(ret!=0){
                return ret;
            }

            return serialNo.compareTo(other.getSerialNo());
        }
        return 1;
    }

    public BundleInfo() {}

    public BundleInfo(Integer serialNo, String qrcode, String largeBundleNumber) {
        super();
        this.serialNo = serialNo;
        this.qrcode = qrcode;
        this.largeBundleNumber = largeBundleNumber;
    }
}
