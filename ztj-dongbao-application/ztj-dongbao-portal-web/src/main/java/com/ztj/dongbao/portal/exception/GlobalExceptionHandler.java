package com.ztj.dongbao.portal.exception;

import com.ztj.dongbao.base.result.ResultWrapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResultWrapper<Object> customerException(){
        return new ResultWrapper().builder().code(500).msg("统一异常").build();
    }
}
