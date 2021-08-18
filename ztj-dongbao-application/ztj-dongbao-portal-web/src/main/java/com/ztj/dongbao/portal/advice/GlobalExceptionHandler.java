package com.ztj.dongbao.portal.advice;

import com.ztj.dongbao.base.exception.TokenException;
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
        return ResultWrapper.getFailBuilder().msg("除零异常").build();
    }

    @ExceptionHandler(TokenException.class)
    public ResultWrapper<Object> tokenCheckException(Exception e){
        return ResultWrapper.getFailBuilder().msg(e.getMessage()).build();
    }

}
