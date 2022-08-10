package com.jairoguo.order.domain.model.value;

/**
 * @author Jairo Guo
 */
public enum OrderStatusEnum {
    /**
     * 未支付
     */
    UNPAID,
    /**
     * 已支付
     */
    PAID,
    /**
     * 已发货
     */
    SHIPPED,
    /**
     * 已收货
     */
    RECEIVED,
    /**
     * 退货中
     */
    RETURNING,
    /**
     * 退货完成
     */
    RETURN_COMPLETED,
    /**
     * 退款
     */
    REFUNDED,
    /**
     * 退款完成
     */
    REFUNDED_COMPLETED,
    /**
     * 订单完成
     */
    COMPLETED
}
