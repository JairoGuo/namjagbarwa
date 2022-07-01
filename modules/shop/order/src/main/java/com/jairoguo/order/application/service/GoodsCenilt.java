package com.jairoguo.order.application.service;

import com.jairoguo.goods.vo.GoodsVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jairo Guo
 */
@FeignClient(name = "goods-server")
public interface GoodsCenilt {

    @GetMapping("goods/test")
    GoodsVO test();
}
