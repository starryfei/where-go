package com.starry.wheregogateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName: GlobalAuthorizeFilter
 * Description: Global token校验
 *
 * @author: starryfei
 * @date: 2019-10-29 23:13
 **/
@Component
@Slf4j
public class GlobalAuthorizeFilter implements GlobalFilter, Ordered {
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * Process the Web request and (optionally) delegate to the next {@code WebFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("访问过滤器");
        ServerHttpRequest request = exchange.getRequest();
        String authStr = request.getHeaders().getFirst("authStr");
        log.info(authStr);
        ServerHttpResponse response = exchange.getResponse();
        if ( authStr == null || "undefined".equals(authStr) ) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            log.info(response.getStatusCode().toString());
            return response.setComplete();
        }
        String[] auth = authStr.split(":");
        if ( auth.length != 2 ) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        String redisToken = redisTemplate.opsForValue().get(auth[0]);
        log.info(redisToken);
        if ( redisToken == null || !authStr.equals(redisToken) ) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
