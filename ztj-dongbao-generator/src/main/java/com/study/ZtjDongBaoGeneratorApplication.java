package com.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.study.mapper")
public class ZtjDongBaoGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZtjDongBaoGeneratorApplication.class,args);
    }
}
