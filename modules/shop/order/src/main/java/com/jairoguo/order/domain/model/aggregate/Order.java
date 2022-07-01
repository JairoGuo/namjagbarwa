package com.jairoguo.order.domain.model.aggregate;

import com.jairoguo.common.base.AggregateRoot;
import com.jairoguo.order.domain.model.entity.id.OrderNumber;
import com.jairoguo.order.domain.model.value.Receiving;

import java.math.BigDecimal;

/**
 * @author Jairo Guo
 */

public class Order implements AggregateRoot<OrderNumber> {


    /**
     * 商品id
     */
    private Long goodsId;

    /** 订单商品总数 */
    private Integer totalNum;

    /** 订单总价 */
    private BigDecimal totalPrice;

    private Receiving receiving;



}