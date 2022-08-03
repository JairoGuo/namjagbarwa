package com.jairoguo.auth.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jairo Guo
 */

@FeignClient(name = "auth-server")
public interface AuthApi {

    @GetMapping("/login/status")
    Boolean isLogin();

    @GetMapping("/login/userId")
    Long getUserId();
}
