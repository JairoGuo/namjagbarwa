package com.jairoguo.order.domain.repository;

import com.jairoguo.common.base.Repository;
import com.jairoguo.common.param.PageParam;
import com.jairoguo.common.result.PageResultBody;
import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.domain.model.entity.id.OrderNumber;

/**
 * @author Jairo Guo
 */
public interface OrderRepository extends Repository<Order, OrderNumber> {

    PageResultBody<Order> list(PageParam param, Order order);
}
