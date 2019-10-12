package com.wrh.copy.vo;

import lombok.Data;

import java.util.List;

/**
 * 节点
 * @author zing
 * @date 2018/4/27 17:32
 */
@Data
public class StageNode {

    /**
     * 节点ID
     */
    private int stageId;

    /**
     * 父节点ID
     */
    private int parentStageId;

    /**
     * 排序编号
     */
    private int sortCode;

    /**
     * 节点名称
     */
    private String stageName;

    /**
     * 三方属性 0自采集，1三方采集，2基本信息
     */
    private Integer thirdPartyFlag;

    /**
     * 节点属性
     */
    private List<NodeProperty> properties;


}
