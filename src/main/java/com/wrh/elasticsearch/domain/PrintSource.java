package com.zcsmart.jzsymodel.trace.collect.industrial.bundle;

import com.alibaba.fastjson.JSON;
import com.zcsmart.jzsy.utils.Digest;
import lombok.Data;

import java.util.Set;

/**
 * Created By @author Zing @date 2018/9/21 18:20
 */
@Data
public class PrintSource {
    /**
     * 操作ID（生成）
     */
    private String bundleOpId;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 班组号
     */
    private String teamNo;
    /**
     * 班组负责人（工号）
     */
    private String teamLeader;
    /**
     * 打印机编号
     */
    private String printerNozzle;
    /**
     * 产线编号
     */
    private String productLineNo;
    /**
     * 企业ID
     */
    private String companyId;
    /**
     * 采集组
     */
    private String groupId;
    /**
     * 卷类型
     */
    private BundleType bundleType;
    /**
     * 大码卷号
     */
    private String largeBundleNumber;
    /**
     * 小卷码号
     */
    private String bundleNumber;
    /**
     * 卷内码数量
     */
    private Integer bundleSize;
    /**
     * 大约4500个一组
     * 卷内码与卷内序号
     */
    private Set<BundleInfo> bundleInfoSet;

    /**
     * 小码卷采集时，可能会有舍弃的码
     */
    private Set<String> discardCodes;

    /**
     * 根据打码信息，生成ID
     *
     * @return
     */
    public String makeBoundId() {
        StringBuffer sb = new StringBuffer();
        bundleInfoSet.stream()
                .map(BundleInfo::getQrcode)
                .sorted(String::compareTo)
                .forEach(sb::append);
        sb.append(groupId).append(bundleType);
        String id = Digest.sha256(JSON.toJSONString(sb));
        return id;
    }
}
