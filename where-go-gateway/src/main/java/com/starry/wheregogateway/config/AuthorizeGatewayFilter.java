package com.starry.wheregogateway.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName: AuthorizeGatewayFilter
 * Description: token校验过滤器
 *
 * @author: starryfei
 * @date: 2019-10-29 22:33
 **/
//@Component
@Slf4j
public class AuthorizeGatewayFilter implements GatewayFilter {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authStr = request.getHeaders().getFirst("authStr");
        log.info(authStr);
        ServerHttpResponse response = exchange.getResponse();
        if(authStr == null || "undefined".equals(authStr) ){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);

            return response.setComplete();
        }
        String[] auth = authStr.split(":");
        if(auth.length != 2){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        String redisToken = redisTemplate.opsForValue().get(auth[0]);
        log.info(redisToken);
        if(redisToken == null || !authStr.equals(redisToken)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }
}
