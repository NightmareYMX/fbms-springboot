package com.ymx.fbms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ymx.com.ymx.fbms.mapper")
public class FbmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FbmsApplication.class, args);
    }

}
