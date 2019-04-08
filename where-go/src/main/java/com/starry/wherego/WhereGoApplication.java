package com.starry.wherego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author starryfei
 */
@SpringBootApplication
@EnableSwagger2
public class WhereGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhereGoApplication.class, args);
    }

}

