package com.starry.wheregogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WhereGoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhereGoGatewayApplication.class, args);
    }

}
