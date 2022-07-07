package com.jairoguo.gateway.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Jairo Guo
 */
@Component
public class AccessLogFilter implements GlobalFilter, Ordered {

    Logger log = LoggerFactory.getLogger(AccessLogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        log.info("Request Method: {}, path: {}, cookies: {}", request.getMethod(), request.getPath(), request.getCookies());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
