package com.jairoguo.order.domain.service;

import com.jairoguo.common.base.DomainService;
import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.domain.model.entity.id.OrderNumber;
import com.jairoguo.order.domain.model.value.OrderStatusEnum;

/**
 * @author Jairo Guo
 */
public interface OrderDomainService extends DomainService {

    void placeOrder(Order order);

    void markOrderStatus(OrderNumber orderNumber, OrderStatusEnum orderStatus);
}
