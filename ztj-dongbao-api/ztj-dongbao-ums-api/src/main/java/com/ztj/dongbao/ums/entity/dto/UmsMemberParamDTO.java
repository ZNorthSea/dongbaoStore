package com.ztj.dongbao.ums.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@ToString
public class UmsMemberParamDTO {
    // 用户名
    @NotEmpty(message = "用户名不能为空")
    @Size(min = 6,max = 8,message = "用户名长度必须在6到8之间")
    private String username;
    // 密码
    private String password;
    //头像
    private String icon;
    //邮箱
    @Email
    private String email;
    //昵称
    @TableField("nick_name")
    private String nickName;
    //备注信息
    private String note;
}
