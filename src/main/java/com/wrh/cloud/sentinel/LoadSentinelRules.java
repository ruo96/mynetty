package com.wrh.cloud.sentinel;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wuruohong
 * @Classname LoadSentinelRules
 * @Description TODO
 * @Date 2021/8/11 20:53
 */
@Component
@Slf4j
public class LoadSentinelRules {
    public LoadSentinelRules(){
        // 初始化加载流控规则
        initFlowRules();
        log.info("加载了流控规则.");

        // 初始化加载熔断降级规则
        initDegradeRules();
        log.info("加载了熔断降级规则.");

    }


    /**
     * 初始化流控规则
     */
    public void initFlowRules(){
        // 流控规则集合
        /*List<FlowRule> flowRules = Lists.newArrayList();

        FlowRule rule = new FlowRule();
        rule.setResource(MySentinelResource.FLOW_RESOURCE);// 资源
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); // 流控阈值类型：qps
        rule.setCount(1);// 流控阈值：1

        flowRules.add(rule);

        FlowRuleManager.loadRules(flowRules);*/
    }

    /**
     * 初始化降级规则
     */
    public void initDegradeRules(){
        /*List<DegradeRule> degradeRules = Lists.newArrayList();

        DegradeRule rule = new DegradeRule();
        rule.setResource(MySentinelResource.DEGRADE_RESOURCE);// 资源
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);// 阈值类型：慢调用比例
        rule.setMinRequestAmount(5);// 最小请求数
        rule.setTimeWindow(1);// 熔断时长，单位秒
        rule.setSlowRatioThreshold(1);// 比例阈值
        rule.setCount(1);// 最大RT 单位毫秒
        rule.setStatIntervalMs(1000);// 统计时长，单位毫秒

        degradeRules.add(rule);

        DegradeRuleManager.loadRules(degradeRules);*/

    }
}
