package com.jairoguo.order.application.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */

@Service
public class OrderApplicationService {

    @Resource
    private GoodsCenilt goodsApi;

    public void test() {
        System.out.println(goodsApi.test());
    }

    public void createOrder() {

    }
}
