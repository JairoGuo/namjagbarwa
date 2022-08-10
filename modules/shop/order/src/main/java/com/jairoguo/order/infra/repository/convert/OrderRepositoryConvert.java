package com.jairoguo.order.infra.repository.convert;

import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.domain.model.entity.id.OrderNumber;
import com.jairoguo.order.infra.repository.po.OrderPO;

import java.util.List;

/**
 * @author Jairo Guo
 */
public class OrderRepositoryConvert {

    public static OrderPO convertToOrderPO(Order item) {
        if (item == null) {
            return null;
        }
        OrderPO result = new OrderPO();
        result.setGoodsId(item.getGoodsId());
        result.setSpecsId(item.getSpecsAttributeId());
        result.setUserId(item.getUserId());
        result.setTotalNum(item.getTotalNum());
        result.setTotalPrice(item.getTotalPrice());
        result.setOrderNumber(item.getOrderNumber().getOrderNumber());
        result.setExtendOrderId(item.getOrderNumber().getExtendOrderId());
        result.setStatus(item.getStatus());
        return result;
    }

    public static Order convertToOrder(OrderPO item) {
        if (item == null) {
            return null;
        }
        Order result = Order.create();
        OrderNumber orderNumber = OrderNumber.newInstance();
        orderNumber.setId(item.getId());
        orderNumber.setOrderNumber(item.getOrderNumber());
        orderNumber.setExtendOrderId(item.getExtendOrderId());
        result.setOrderNumber(orderNumber);
        result.setGoodsId(item.getGoodsId());
        result.setSpecsAttributeId(item.getSpecsId());
        result.setUserId(item.getUserId());
        result.setTotalNum(item.getTotalNum());
        result.setTotalPrice(item.getTotalPrice());
        result.setReceiving(null);
        result.setStatus(item.getStatus());
        return result;
    }


    public static List<Order> convertToList(List<OrderPO> item) {
        if (item == null) {
            return null;
        }
        return item.stream().map(OrderRepositoryConvert::convertToOrder).toList();
    }

}
