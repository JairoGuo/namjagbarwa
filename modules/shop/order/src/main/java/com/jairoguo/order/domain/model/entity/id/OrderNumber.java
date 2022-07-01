package com.jairoguo.order.domain.model.entity.id;


import com.jairoguo.common.base.Id;

/**
 * @author Jairo Guo
 */
public class OrderNumber implements Id {

    private Long id;
    private Long orderNumber;
    /** 额外订单号 */
    private String extendOrderId;
}