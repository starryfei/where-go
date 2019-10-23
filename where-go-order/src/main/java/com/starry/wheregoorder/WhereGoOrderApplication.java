package com.starry.wheregoorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients


public class WhereGoOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhereGoOrderApplication.class, args);
    }

}
