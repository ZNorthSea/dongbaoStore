package com.ztj.test;

import com.ztj.dongbao.ums.ZtjDongBaoUmsApplication;
import com.ztj.dongbao.ums.entity.UmsMember;
import com.ztj.dongbao.ums.mapper.UmsMemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(classes = ZtjDongBaoUmsApplication.class)
public class UmsMemberTest {
    @Autowired
    private UmsMemberMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void selectByUserNameTest(){
        UmsMember umsMember = mapper.selectByUserName("lisi");
        System.out.println("umsMember = " + umsMember);
    }

    @Test
    void passwordTest(){
        String password = "123";
        String encode = passwordEncoder.encode(password);
        System.out.println("encode = " + encode);
        boolean matches = passwordEncoder.matches(encode, password);
        System.out.println("matches = " + matches);
    }


}
