package com.ztj.dongbao.ums.service;


import com.ztj.dongbao.base.result.ResultWrapper;
import com.ztj.dongbao.ums.entity.UmsMember;
import com.ztj.dongbao.ums.entity.dto.UmsMemberParamDTO;

/**
 * 后台用户表(UmsMember)表服务接口
 *
 * @author ztj
 * @since 2021-08-16 00:37:16
 */
public interface UmsMemberService {
    /**
     * 注册功能
     * @param umsMemberParamDTO
     * @return
     */
    ResultWrapper<Object> register(UmsMemberParamDTO umsMemberParamDTO);

    /**
     * 登陆功能
     * @param umsMemberParamDTO
     * @return
     */
    ResultWrapper<Object> login(UmsMemberParamDTO umsMemberParamDTO);

    /**
     * 修改功能
     * @param umsMember
     * @return
     */
    ResultWrapper<Object> update(UmsMember umsMember);
}
