package com.starry.wherego.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WhereGoEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhereGoEmailApplication.class, args);
    }

}
