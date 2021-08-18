package com.ztj.dongbao.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztj.dongbao.ums.entity.UmsMember;
import org.springframework.stereotype.Repository;

/**
 * 后台用户表(UmsMember)表数据库访问层
 *
 * @author ztj
 * @since 2021-08-16 00:43:58
 */
@Repository
public interface UmsMemberMapper extends BaseMapper<UmsMember> {

    UmsMember selectByUserName(String username);
    Integer selectCountByUserName(String username);
}
