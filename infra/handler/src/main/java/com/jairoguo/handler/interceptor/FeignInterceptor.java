package com.jairoguo.handler.interceptor;

import com.jairoguo.common.FeignUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author Jairo Guo
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    Logger log = LoggerFactory.getLogger(FeignInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {

        log.info("FeignInterceptor FeignUtil.FEIGN_ID(feign_id): {}", requestTemplate.headers().get(FeignUtil.FEIGN_ID));
        if (requestTemplate.headers().get(FeignUtil.FEIGN_ID) == null) {
            requestTemplate.header(FeignUtil.FEIGN_ID, String.valueOf(UUID.randomUUID()));
        }
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes != null ? requestAttributes.getRequest() : null;
        /* TODO: 部分头参数影响系统运行
        Enumeration<String> headerNames = request.getHeaderNames();
        if (Objects.nonNull(headerNames)) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                requestTemplate.header(name, request.getHeader(name));
            }
        }
        */
        if (request != null) {
            requestTemplate.header("cookie", request.getHeader("cookie"));

        }
    }
}
