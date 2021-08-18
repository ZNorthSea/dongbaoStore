package com.ztj.dongbao.ums;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ztj.dongbao.ums.mapper")
public class ZtjDongBaoUmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZtjDongBaoUmsApplication.class,args);
    }
}
