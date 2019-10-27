package com.starry.wheregouser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.starry.wheregouser.mapper")

public class WhereGoUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhereGoUserApplication.class, args);
    }

}
