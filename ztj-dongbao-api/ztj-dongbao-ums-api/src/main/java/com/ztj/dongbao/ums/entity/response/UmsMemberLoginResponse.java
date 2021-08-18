package com.ztj.dongbao.ums.entity.response;

import com.ztj.dongbao.ums.entity.UmsMember;
import lombok.Data;

@Data
public class UmsMemberLoginResponse {
    private String token;
    private UmsMember umsMember;
}
