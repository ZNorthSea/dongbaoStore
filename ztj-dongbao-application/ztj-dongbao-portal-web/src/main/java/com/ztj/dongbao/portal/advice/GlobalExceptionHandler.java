package com.ztj.dongbao.portal.advice;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
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

    // token异常处理
    @ExceptionHandler(TokenException.class)
    public ResultWrapper<Object> tokenCheckException(Exception e){
        return ResultWrapper.getFailBuilder().msg(e.getMessage()).build();
    }

    @ExceptionHandler(value = KaptchaException.class)
    public String kaptchaExceptionHandler(KaptchaException kaptchaException) {
        if (kaptchaException instanceof KaptchaIncorrectException) {
            return "验证码不正确";
        } else if (kaptchaException instanceof KaptchaNotFoundException) {
            return "验证码未找到";
        } else if (kaptchaException instanceof KaptchaTimeoutException) {
            return "验证码过期";
        } else {
            return "验证码渲染失败";
        }

    }

}
