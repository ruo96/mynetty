package com.wrh.lambda.stream.vo;

import lombok.Data;

import java.util.Set;

/**
 * 印码采集
 * Created By @author Zing @date 2018/9/17 19:04
 */
@Data
public class CodePrintInfo {

    /**
     * 码卷内序号
     */
    private Set<BundleInfo> bundleInfoSet;


}
