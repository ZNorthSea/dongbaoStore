package com.ztj.dongbao.base.result;

import com.ztj.dongbao.base.enums.ShowStatesEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.PortableInterceptor.SUCCESSFUL;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultWrapper<T> implements Serializable {
    // 状态码
    private int code;

    // 提示信息
    private String msg;

    // 返回的数据
    private T data;

    public static ResultWrapper.ResultWrapperBuilder getSuccessBuilder(){
        return ResultWrapper.builder().code(ShowStatesEnum.SUCCESS.getCode())
                .msg(ShowStatesEnum.SUCCESS.getMsg());
    }

    public static ResultWrapper.ResultWrapperBuilder getFailBuilder(){
        return ResultWrapper.builder().code(ShowStatesEnum.FAIL.getCode())
                .msg(ShowStatesEnum.FAIL.getMsg());
    }
}