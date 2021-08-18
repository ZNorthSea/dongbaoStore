package com.ztj.dongbao.ums.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台用户表(UmsMember)表实体类
 *
 * @author ztj
 * @since 2021-08-16 00:37:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_member")
public class UmsMember implements Serializable {
    @TableId(
            type = IdType.AUTO
    )
    private Long id;
    // 用户名
    private String username;
    // 密码
    private String password;
    //头像
    private String icon;
    //邮箱
    private String email;
    //昵称
    @TableField("nick_name")
    private String nickName;
    //备注信息
    private String note;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    //最后登录时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    //帐号启用状态：0->禁用；1->启用
    private Integer status;

}
