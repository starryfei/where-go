package com.starry.wheregomap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * ClassName: RestTemplateConfig
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-02-12 11:10
 **/
@Configuration
public class RestTemplateConfig {
    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate(){
        // 使用HttpClient，支持GZIP
        RestTemplate restTemplate = new RestTemplate();
        // 支持中文编码
        restTemplate.getMessageConverters().set(1,
                new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

}
