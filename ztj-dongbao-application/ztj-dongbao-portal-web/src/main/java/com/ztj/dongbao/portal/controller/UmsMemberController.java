package com.ztj.dongbao.portal.controller;


import com.ztj.dongbao.base.result.ResultWrapper;
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
    public ResultWrapper register(@RequestBody @Validated UmsMemberParamDTO umsMember){
        // int i = 1/0;
        return ResultWrapper.getSuccessBuilder().data(null).build();
    }

    @PostMapping("/login")
    public String login(@RequestBody UmsMemberParamDTO umsMember){
        return umsMemberService.login(umsMember);
    }
}
