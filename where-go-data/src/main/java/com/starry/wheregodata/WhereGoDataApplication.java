package com.starry.wheregodata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.starry.wheregodata.mapper")
@EnableEurekaClient
@EnableFeignClients

public class WhereGoDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhereGoDataApplication.class, args);
    }

}
