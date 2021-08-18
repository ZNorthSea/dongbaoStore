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

    /**
     * 请求成功，状态码为200时调用
     * @return
     */
    public static ResultWrapper.ResultWrapperBuilder<Object> getSuccessBuilder(){
        return ResultWrapper.builder().code(ShowStatesEnum.SUCCESS.getCode())
                .msg(ShowStatesEnum.SUCCESS.getMsg());
    }

    /**
     * 请求失败，出现500异常调用
     * @return
     */
    public static ResultWrapper.ResultWrapperBuilder<Object> getFailBuilder(){
        return ResultWrapper.builder().code(ShowStatesEnum.FAIL.getCode())
                .msg(ShowStatesEnum.FAIL.getMsg());
    }

    /**
     * 请求失败，出现密码不正确时调用
     * @return
     */
    public static ResultWrapper.ResultWrapperBuilder<Object> getPasswordFail() {
        return ResultWrapper.builder().code(ShowStatesEnum.PASSWORD_ERROR.getCode())
                .msg(ShowStatesEnum.PASSWORD_ERROR.getMsg());
    }

    /**
     * 请求失败，出现用户不存在时调用
     * @return
     */
    public static ResultWrapper.ResultWrapperBuilder<Object> getUserEmptyFail() {
        return ResultWrapper.builder().code(ShowStatesEnum.USER_EMPTY.getCode())
                .msg(ShowStatesEnum.USER_EMPTY.getMsg());
    }


}