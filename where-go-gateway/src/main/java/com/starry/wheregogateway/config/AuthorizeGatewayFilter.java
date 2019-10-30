package com.starry.wheregogateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName: AuthorizeGatewayFilter
 * Description: 过滤器做特殊校验，现没有起作用
 *
 * @author: starryfei
 * @date: 2019-10-29 22:33
 **/
@Slf4j
public class AuthorizeGatewayFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        log.info(request.getId());

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
