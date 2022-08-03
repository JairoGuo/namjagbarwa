package com.jairoguo.order.interfaces.rest.convert;

import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.dto.OrderDTO;

/**
 * @author Jairo Guo
 */
public class OrderConvert {

    public static Order convertToOrder(OrderDTO item) {
        if (item == null) {
            return null;
        }
        Order result = Order.create();
        result.setGoodsId(item.specsAttributeId());
        result.setTotalNum(item.total());
        return result;
    }
}
