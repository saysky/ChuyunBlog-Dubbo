package com.example.blog.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.example.blog.core.mapper*")
public class BlogCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogCoreApplication.class, args);
    }

}
