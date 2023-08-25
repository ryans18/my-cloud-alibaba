package com.ryans.sentinel.flow.rule;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Author : Ryans
 * Date : 2023/8/25
 * Introduction :
 */
public class FlowFunction implements InitFunc {
    @Override
    public void init() throws Exception {
        List<FlowRule> ruleList = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("hello");
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 允许最大流量1s内2次
        flowRule.setCount(2);
        ruleList.add(flowRule);
        FlowRuleManager.loadRules(ruleList);
    }
}
