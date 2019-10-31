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

import java.net.URI;
import java.util.Objects;

import static com.starry.wheregogateway.common.FilterPathCommon.*;


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

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取路由信息
//        Route gatewayUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        ServerHttpRequest originalRequest = exchange.getRequest();
        URI originalRequestUrl = originalRequest.getURI();
        String routePath = originalRequestUrl.getPath();
        log.info(originalRequestUrl.getHost()+": "+originalRequestUrl.getPath());
        // 不限制登陆和注册访问请求
        if(Objects.equals(LOGIN,routePath) || Objects.equals(REGISTER,routePath)) {
            return chain.filter(exchange);
        }
        ServerHttpRequest request = exchange.getRequest();
        String authStr = request.getHeaders().getFirst("authStr");
        log.info(authStr);
        ServerHttpResponse response = exchange.getResponse();
        if (authStr == null || UNDIFINE.equals(authStr) ) {
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
        return 0;
    }
}
