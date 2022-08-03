package com.jairoguo.order.infra.repository.impl;

import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.domain.model.entity.id.OrderNumber;
import com.jairoguo.order.domain.repository.OrderRepository;
import com.jairoguo.order.infra.repository.convert.OrderToPO;
import com.jairoguo.order.infra.repository.mapper.OrderMapper;
import com.jairoguo.order.infra.repository.po.OrderPO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Resource
    private OrderMapper orderMapper;

    // @Resource
    // private ServiceImpl<OrderMapper, OrderPO> orderMapperService;

    @Override
    public Boolean save(Order aggregate) {
        OrderPO orderPO = OrderToPO.convertToOrderPO(aggregate);
        return orderMapper.insert(orderPO) != 0;
    }

    @Override
    public Order findById(OrderNumber id) {
        return null;
    }

    @Override
    public Boolean delete(OrderNumber id) {
        return null;
    }

    @Override
    public Boolean update(Order aggregate) {
        return null;
    }
}
