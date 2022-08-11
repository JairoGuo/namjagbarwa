package com.jairoguo.order.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
        int insert = orderMapper.insert(orderPO);
        if (insert != 0) {
            aggregate.getOrderNumber().setId(orderPO.getId());
            return true;
        }

        return false;

    }

    @Override
    public Order findById(OrderNumber id) {
        QueryWrapper<OrderPO> orderPOQueryWrapper = new QueryWrapper<>();

        if (id.getOrderNumber() != null) {
            orderPOQueryWrapper.lambda()
                    .eq(OrderPO::getOrderNumber, id.getOrderNumber());
        }

        if (id.getId() != null) {
            orderPOQueryWrapper.lambda()
                    .eq(OrderPO::getId, id.getId());
        }


        OrderPO orderPO = orderMapper.selectOne(orderPOQueryWrapper);
        return OrderRepositoryConvert.convertToOrder(orderPO);
    }

    @Override
    public Boolean delete(OrderNumber id) {
        return null;
    }

    @Override
    public Boolean update(Order aggregate) {
        Long orderId;
        OrderPO orderPO = OrderRepositoryConvert.convertToOrderPO(aggregate);
        if ((orderId = aggregate.getOrderNumber().getId()) != null) {
            orderPO.setId(orderId);
            return orderMapper.updateById(orderPO) > 0;

        } else {
            UpdateWrapper<OrderPO> orderPOUpdateWrapper = new UpdateWrapper<>();
            orderPOUpdateWrapper.lambda()
                    .eq(OrderPO::getSpecsId, aggregate.getSpecsAttributeId());
            return orderMapper.update(orderPO, orderPOUpdateWrapper) > 0;
        }
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
