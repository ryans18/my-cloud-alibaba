package com.ryans.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryans.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 * Author：Ryans
 * Date：Created in 2023/8/25 22:11
 * Introduction：
 */
@Component
public class UrlBlockHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException exception) throws Exception {
        String msg = null;
        if (exception instanceof FlowException) { // 限流异常
            msg = "接口已被限流";
        } else if (exception instanceof DegradeException) { // 熔断降级
            msg = "接口已被熔断，请稍后再试";
        } else if (exception instanceof ParamFlowException) { // 热点参数异常
            msg = "热点参数限流";
        } else if (exception instanceof SystemBlockException) {  // 系统改规则异常
            msg = "系统规则(负载不满足要求)";
        } else if (exception instanceof AuthorityException) {  // 授权规则异常
            msg = "授权规则不通过";
        }
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        // ObjectMapper是jackson内置的json转换工具，用于将对象转换为json字符串。
        ObjectMapper mapper = new ObjectMapper();
        // 某个对象为null时不进行序列化输出
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.writeValue(httpServletResponse.getWriter(), Result.fail(msg));
    }
}