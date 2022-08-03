package com.jairoguo.order.application.service;

import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.domain.service.OrderDomainService;
import com.jairoguo.order.infra.api.AuthApiService;
import com.jairoguo.order.infra.api.GoodsApiService;
import com.jairoguo.redis.util.RedisKey;
import com.jairoguo.redis.util.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */

@Service
public class OrderApplicationService {

    @Resource
    private AuthApiService authApiService;

    @Resource
    private GoodsApiService goodsApiService;
    @Resource
    private OrderDomainService orderDomainService;

    @Resource
    private RedisKey redisKey;

    @Resource
    private RedisUtils redisUtils;

    @Transactional
    public void createOrder(Order order) {

        order.calculatePrice();

        // 调用领域服务完成下单
        orderDomainService.placeOrder(order);

        // 通知商品减库存
        goodsApiService.deductions(order.getSpecsAttributeId(), order.getTotalNum());


    }
}
