package com.wrh.list.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 */
@Getter
@Setter
public class CrowdRuleEngineInfo {

    /**
     * 规则id
     */
    private Integer id;

    /**
     * 规则内容
     */
    private String ruleName;

    /**
     * 游戏基础id
     */
    private Integer gameBaseId;

    /**
     * sdk类型id   可以多选
     */
    private List<Integer> sdkTypeId;

    /**
     * 游戏id   可以多选
     */
    private List<Integer> gameId;

    /**
     * 业务类型  0-礼包推荐 1-玩家活跃度预测 2-神秘商人 3-社交推荐  单选
     */
    private Integer businessType;

    /**
     * 准入用户 0-低活跃，1-预流失，2-回流   可以多选
     */
    private List<Integer> churnType;

    /**
     * 分群ID，更新时定位具体人群
     * 有效长度固定16位
     */
    private String crowdId;

    /**
     * 规则刷新时间  单位：分钟  默认5
     */
    private Integer refreshRate;

    /**
     * 规则状态   1: 启用    0: 停用
     */
    private Integer ruleStatus;

    /**
     * 规则内容
     */
    private String ruleContent;

    /**
     * 人群预估数量
     */
    private Integer crowdNum;

    /**
     * 通知下游标识  0：未通知   1：已通知
     */
    private Integer notifyFlag;

    /**
     * 创建人
     */
    private String creator;

    /** 创建时间*/
    @JSONField( format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ctime;

    /** 更新时间*/
    @JSONField( format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime mtime;
}
