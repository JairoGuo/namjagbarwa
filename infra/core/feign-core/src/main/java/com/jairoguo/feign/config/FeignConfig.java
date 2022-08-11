package com.jairoguo.feign.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


/**
 * @author Jairo Guo
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    public Retryer feignRetryer() {
        return new Retryer.Default(
                100,
                TimeUnit.SECONDS.toMillis(1),
                5
        );
    }


    Request.Options options() {
        return new Request.Options(
                1, TimeUnit.MICROSECONDS,
                1, TimeUnit.MILLISECONDS,
                true
        );
    }
}
