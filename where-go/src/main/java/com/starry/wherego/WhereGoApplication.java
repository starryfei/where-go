package com.starry.wherego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author starryfei
 */
@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class WhereGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhereGoApplication.class, args);
    }

}

