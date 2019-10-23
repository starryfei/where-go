package com.starry.wheregoorder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: RestConfig
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-10-22 23:14
 **/
@Configuration
public class RestConfig {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
