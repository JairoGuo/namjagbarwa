package com.jairoguo.auth.application.service;

import cn.dev33.satoken.stp.StpUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Jairo Guo
 */

@Service
public class AuthApplicationService {
    Cache<String, Boolean> loginStatusCache = Caffeine.newBuilder()
            .expireAfterWrite(10L, TimeUnit.MINUTES)
            .maximumSize(1_000_000)
            .build();

    public Boolean isLogIn() {
        String tokenValue = StpUtil.getTokenValue();
        if (tokenValue == null) {
            return false;
        }
        Boolean value = loginStatusCache.getIfPresent(tokenValue);
        if (Boolean.TRUE.equals(value)) {
            return true;
        }

        if (StpUtil.isLogin()) {
            loginStatusCache.put(StpUtil.getTokenValue(), true);
            return true;
        }

        return false;
    }
}
