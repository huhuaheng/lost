package com.tomhuwd.lost;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tomhuwd.lost.mapper")
public class LostApplication {

    public static void main(String[] args) {
        SpringApplication.run(LostApplication.class, args);
    }

}
