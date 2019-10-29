package com.starry.wheregogateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: RouteConfig
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-10-29 22:46
 **/
@Configuration
@Slf4j
public class RouteConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        //网关访问/user/list时，如果token验证通过，会转发到 http://localhost:8077/api/user/list
        return builder.routes().route(r ->
                        r.path("*")
                        .uri("http://localhost:8098/send")
                        .id("authon").filter(new AuthorizeGatewayFilter()))
        .build();
    }

}
