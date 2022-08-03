package com.jairoguo.order.domain.model.aggregate;

import com.jairoguo.common.base.AggregateRoot;
import com.jairoguo.order.domain.model.entity.id.OrderNumber;
import com.jairoguo.order.domain.model.value.OrderStatusEnum;
import com.jairoguo.order.domain.model.value.Receiving;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Jairo Guo
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class Order implements AggregateRoot<OrderNumber> {

    private OrderNumber orderNumber;
    /**
     * 商品id
     */
    private Long goodsId;

    private Long specsAttributeId;

    private Long userId;

    /** 订单商品总数 */
    private Long totalNum;

    /** 订单总价 */
    private BigDecimal totalPrice;

    /** 商品单价 */
    private BigDecimal price;
    /**
     * 收货地址
     */
    private Receiving receiving;
    /**
     * 订单状态
     */
    private OrderStatusEnum status;


    public void calculatePrice() {
        BigDecimal total = price.multiply(new BigDecimal(this.getTotalNum()));
        this.setTotalPrice(total);
    }
    public static Order create() {
        return new Order();
    }
}