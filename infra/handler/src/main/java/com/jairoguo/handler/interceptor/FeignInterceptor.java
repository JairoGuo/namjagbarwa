package com.jairoguo.handler.interceptor;

import com.jairoguo.common.FeignUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @author Jairo Guo
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    Logger log = LoggerFactory.getLogger(FeignInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {

        log.info("FeignInterceptor FeignUtil.FEIGN_ID(feign_id) 是否存在: {}", requestTemplate.headers().get(FeignUtil.FEIGN_ID) == null);
        if (requestTemplate.headers().get(FeignUtil.FEIGN_ID) == null) {
            requestTemplate.header(FeignUtil.FEIGN_ID, String.valueOf(UUID.randomUUID()));
        }
        log.info("FeignInterceptor FeignUtil.FEIGN_ID(feign_id): {}", requestTemplate.headers().get(FeignUtil.FEIGN_ID));


    }
}
