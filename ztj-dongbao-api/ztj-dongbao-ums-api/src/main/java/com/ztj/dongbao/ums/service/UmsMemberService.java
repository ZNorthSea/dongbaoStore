package com.ztj.dongbao.ums.service;


import com.ztj.dongbao.ums.entity.dto.UmsMemberParamDTO;

/**
 * 后台用户表(UmsMember)表服务接口
 *
 * @author ztj
 * @since 2021-08-16 00:37:16
 */
public interface UmsMemberService {
    String register(UmsMemberParamDTO umsMemberParamDTO);
    String login(UmsMemberParamDTO umsMemberParamDTO);
}
