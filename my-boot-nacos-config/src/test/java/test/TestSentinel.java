package test;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.junit.Test;
import java.util.*;


/**
 * Author : Ryans
 * Date : 2023/8/25
 * Introduction :
 */
public class TestSentinel {

    @Test
    public void testFlowQPS() {
        // 配置规则
        initFlowRules();
        while (true) {
            try(Entry entry = SphU.entry("HelloWorld")) {
                // 被保护的逻辑
                System.out.println("hello, world   ------" + System.currentTimeMillis()/1000);
            } catch (BlockException e) {
                // 处理被拒绝的逻辑
            }
        }
    }
    private void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        // 资源名，限流规则的作用对象
        flowRule.setResource("HelloWorld");
        // 限制阈值类型，QPS或线程数模式
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 限流的阈值，每秒处理请求8个
        flowRule.setCount(8);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }
}
