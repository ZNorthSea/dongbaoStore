package com.ztj.dongbao.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ztj.dongbao.base.enums.ShowStatesEnum;
import com.ztj.dongbao.base.result.ResultWrapper;
import com.ztj.dongbao.common.util.JwtUtil;
import com.ztj.dongbao.ums.entity.UmsMember;
import com.ztj.dongbao.ums.entity.dto.UmsMemberParamDTO;
import com.ztj.dongbao.ums.entity.response.UmsMemberLoginResponse;
import com.ztj.dongbao.ums.mapper.UmsMemberMapper;
import com.ztj.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private UmsMemberMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 用户注册
    @Override
    public ResultWrapper<Object> register(UmsMemberParamDTO umsMemberParamDTO) {
        // 判断前端传入的验证码与Redis中存入的验证码是否一致
        // 如果一致则进行下一步，如果不一致，则直接返回 ResultWrapper.getFailBuilder().data(null).build()

        // 防止重名
        Integer count = mapper.selectCountByUserName(umsMemberParamDTO.getUsername());
        System.out.println("count = " + count);
        if (count < 1){
            // 对密码做加密处理 原则上讲：向数据库中插入数据是要做到脱敏(脱除敏感信息)
            String password = passwordEncoder.encode(umsMemberParamDTO.getPassword());
            umsMemberParamDTO.setPassword(password);

            UmsMember umsMember = new UmsMember();
            BeanUtils.copyProperties(umsMemberParamDTO,umsMember);
            int result = mapper.insert(umsMember);

            return ResultWrapper.getSuccessBuilder().data(result).build();
        }
        return new ResultWrapper<>().builder()
                .code(ShowStatesEnum.USERNAME_REPEAD.getCode())
                .msg(ShowStatesEnum.USERNAME_REPEAD.getMsg())
                .data(null)
                .build();
    }

    // 用户登陆
    @Override
    public ResultWrapper<Object> login(UmsMemberParamDTO umsMemberParamDTO) {
        // 查询数据库中用户信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",umsMemberParamDTO.getUsername());
        UmsMember umsMember = mapper.selectOne(queryWrapper);
        // 判断用户是否存在
        if (null != umsMember){
            boolean result = passwordEncoder.matches(umsMemberParamDTO.getPassword(), umsMember.getPassword());
            if (!result){
                return ResultWrapper.getPasswordFail()
                        .data(null)
                        .build();
            }
        }else {
            return ResultWrapper.getUserEmptyFail()
                    .data(null)
                    .build();
        }
        // 以用户编号为Jwt要编码的值来获取token
        String token = JwtUtil.createToken(umsMember.getId() + "");

        UmsMemberLoginResponse umsMemberLoginResponse = new UmsMemberLoginResponse();
        umsMemberLoginResponse.setToken(token);
        umsMemberLoginResponse.setUmsMember(umsMember);
        return ResultWrapper.getSuccessBuilder()
                .data(umsMemberLoginResponse)
                .build();
    }

    @Override
    public ResultWrapper<Object> update(UmsMember umsMember) {
        String password = passwordEncoder.encode(umsMember.getPassword());
        umsMember.setPassword(password);
        int i = mapper.updateById(umsMember);
        System.out.println("i = " + i);
        return ResultWrapper.getSuccessBuilder()
                .data(umsMember)
                .build();
    }
}
