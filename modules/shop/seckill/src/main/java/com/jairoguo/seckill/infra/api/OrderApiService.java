package com.jairoguo.seckill.infra.api;

import com.jairoguo.order.api.OrderApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Jairo Guo
 */

@FeignClient("order-server")
public interface OrderApiService  extends OrderApi {
}
