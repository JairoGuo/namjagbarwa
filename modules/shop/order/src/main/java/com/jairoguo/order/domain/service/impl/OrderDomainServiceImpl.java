package com.jairoguo.order.domain.service.impl;

import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.domain.model.entity.id.OrderNumber;
import com.jairoguo.order.domain.repository.OrderRepository;
import com.jairoguo.order.domain.service.OrderDomainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */

@Service
public class OrderDomainServiceImpl implements OrderDomainService {


    @Resource
    private OrderRepository orderRepository;
    @Override
    public void placeOrder(Order order) {

        // 生成订单号
        OrderNumber orderNumber = OrderNumber.create();
        order.setOrderNumber(orderNumber);

        orderRepository.save(order);

    }
}
