package com.ztj.dongbao.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ztj.dongbao.ums.entity.UmsMember;
import com.ztj.dongbao.ums.entity.dto.UmsMemberParamDTO;
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
    public String register(UmsMemberParamDTO umsMemberParamDTO) {
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

            return Integer.toString(result);
        }
        return "用户名重复";
    }

    // 用户登陆
    @Override
    public String login(UmsMemberParamDTO umsMemberParamDTO) {
        // 查询数据库中用户信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",umsMemberParamDTO.getUsername());
        UmsMember umsMember = mapper.selectOne(queryWrapper);
        // 判断用户是否存在
        if (null != umsMember){
            boolean result = passwordEncoder.matches(umsMemberParamDTO.getPassword(), umsMember.getPassword());
            if (!result){
                return "密码错误";
            }
        }else {
            return "用户名不存在";
        }
        return "登陆成功";
    }
}
