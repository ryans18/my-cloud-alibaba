package com.ryans.exception;

import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Author : Ryans
 * Date : 2023/8/25
 * Introduction :
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = FlowException.class)
    public String flowException() {
        return "触发限流";
    }
}
