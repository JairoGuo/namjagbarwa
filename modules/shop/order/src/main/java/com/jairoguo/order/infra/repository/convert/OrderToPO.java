package com.jairoguo.order.infra.repository.convert;

import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.infra.repository.po.OrderPO;

/**
 * @author Jairo Guo
 */
public class OrderToPO {

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
        return result;
    }
}
