package com.jairoguo.seckill.infra.api;

import com.jairoguo.goods.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Jairo Guo
 */

@FeignClient(name = "goods-server")
public interface GoodsApiService extends GoodsApi {
}
