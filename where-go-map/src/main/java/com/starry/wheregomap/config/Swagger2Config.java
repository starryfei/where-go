package com.starry.wheregomap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * ClassName: Swagger2Config
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-03-27 17:45
 **/
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //指向自己的controller即可
                .apis(RequestHandlerSelectors.basePackage("com.starry.wherego.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 设置文档信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("SpringBoot中使用Swagger2构建RESTful API")
                //描述
                .description("<a href='github.com/starryfei'>github</a>")
                //版本号
                .version("1.0")
                .build();

    }
}
