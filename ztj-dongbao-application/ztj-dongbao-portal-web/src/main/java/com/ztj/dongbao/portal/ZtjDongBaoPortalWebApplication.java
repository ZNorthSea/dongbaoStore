package com.ztj.dongbao.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ztj.dongbao")
public class ZtjDongBaoPortalWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZtjDongBaoPortalWebApplication.class,args);
    }
}
