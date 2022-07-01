package com.jairoguo.handler.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @author Jairo Guo
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        if (requestTemplate.headers().get("type") == null) {
            requestTemplate.header("type", String.valueOf(UUID.randomUUID()));
        }

    }
}
