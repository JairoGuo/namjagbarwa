package com.jairoguo.goods.infra.api;

import com.jairoguo.auth.api.AuthApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Jairo Guo
 */

@FeignClient(name = "auth-server")
public interface AuthApiService extends AuthApi {
}
