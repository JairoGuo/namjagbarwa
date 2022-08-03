package com.jairoguo.order.domain.service;

import com.jairoguo.common.base.DomainService;
import com.jairoguo.order.domain.model.aggregate.Order;

/**
 * @author Jairo Guo
 */
public interface OrderDomainService extends DomainService {

    void placeOrder(Order order);
}
