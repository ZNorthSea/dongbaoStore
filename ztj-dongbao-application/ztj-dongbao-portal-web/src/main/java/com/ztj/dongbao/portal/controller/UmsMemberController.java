package com.ztj.dongbao.portal.controller;


import com.ztj.dongbao.base.annotations.TokenCheck;
import com.ztj.dongbao.base.result.ResultWrapper;
import com.ztj.dongbao.common.util.JwtUtil;
import com.ztj.dongbao.ums.entity.UmsMember;
import com.ztj.dongbao.ums.entity.dto.UmsMemberParamDTO;
import com.ztj.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 后台用户表(UmsMember)表控制层
 *
 * @author ztj
 * @since 2021-08-16 00:44:21
 */
@RestController
@RequestMapping("/ums_member")
public class UmsMemberController {
    @Autowired
    private UmsMemberService umsMemberService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/register")
    public ResultWrapper<Object> register(@RequestBody @Validated UmsMemberParamDTO umsMember){
        // int i = 1/0;
        return umsMemberService.register(umsMember);
    }

    @PostMapping("/login")
    public ResultWrapper<Object> login(@RequestBody UmsMemberParamDTO umsMemberParamDTO){
        return umsMemberService.login(umsMemberParamDTO);
    }

    @PostMapping("/update")
    @TokenCheck
    public ResultWrapper<Object> update(@RequestBody UmsMember umsMember){
        return umsMemberService.update(umsMember);
    }


    // 测试token
    @GetMapping("test-verify")
    public String verify(String token){
        String token1 = JwtUtil.parseToken(token);
        return token1;
    }
}
