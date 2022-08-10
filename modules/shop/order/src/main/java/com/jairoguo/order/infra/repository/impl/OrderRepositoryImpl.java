package com.jairoguo.order.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jairoguo.common.param.PageParam;
import com.jairoguo.common.result.PageResult;
import com.jairoguo.common.result.PageResultBody;
import com.jairoguo.database.entity.PageForMP;
import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.domain.model.entity.id.OrderNumber;
import com.jairoguo.order.domain.repository.OrderRepository;
import com.jairoguo.order.infra.repository.convert.OrderRepositoryConvert;
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



    @Override
    public Boolean save(Order aggregate) {
        OrderPO orderPO = OrderRepositoryConvert.convertToOrderPO(aggregate);
        return orderMapper.insert(orderPO) != 0;
    }

    @Override
    public Order findById(OrderNumber id) {
        QueryWrapper<OrderPO> orderPOQueryWrapper = new QueryWrapper<>();
        orderPOQueryWrapper.lambda()
                .eq(OrderPO::getOrderNumber, id.getOrderNumber());

        OrderPO orderPO = orderMapper.selectOne(orderPOQueryWrapper);
        return OrderRepositoryConvert.convertToOrder(orderPO);
    }

    @Override
    public Boolean delete(OrderNumber id) {
        return null;
    }

    @Override
    public Boolean update(Order aggregate) {
        return null;
    }


    @Override
    public PageResultBody<Order> list(PageParam pageParam, Order order) {

        QueryWrapper<OrderPO> orderPOQueryWrapper = new QueryWrapper<>();
        orderPOQueryWrapper.lambda()
                .eq(OrderPO::getUserId, order.getUserId());

        Page<OrderPO> orderPOPage = orderMapper.selectPage(
                Page.of(pageParam.getCurrentPage(), pageParam.getPageSize()), orderPOQueryWrapper);

        return PageResult.of(PageForMP.of(orderPOPage, OrderRepositoryConvert.convertToList(orderPOPage.getRecords())));

    }
}
