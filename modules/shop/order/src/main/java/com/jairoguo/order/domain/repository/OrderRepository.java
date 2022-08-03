package com.jairoguo.order.domain.repository;

import com.jairoguo.common.base.Repository;
import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.domain.model.entity.id.OrderNumber;

/**
 * @author Jairo Guo
 */
public interface OrderRepository extends Repository<Order, OrderNumber> {
}
