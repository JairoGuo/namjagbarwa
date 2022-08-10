package com.jairoguo.order.infra.repository.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jairoguo.database.entity.BaseEntity;
import com.jairoguo.order.domain.model.value.OrderStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Jairo Guo
 */
@Getter
@Setter
@Entity
@TableName("t_order")
@Table(name = "t_order", indexes = {
        @Index(columnList = "orderNumber")
})
public class OrderPO extends BaseEntity {

    private Long orderNumber;
    private String extendOrderId;
    private Long goodsId;
    private Long specsId;

    private Long userId;

    /** 订单商品总数 */
    private Long totalNum;

    /** 订单总价 */
    private BigDecimal totalPrice;

    @Column(length = 20, columnDefinition = "varchar(20)")
    private OrderStatusEnum status;

    /**
     * 用户姓名
     */
    private String realName;

    /**
     * 用户电话
     */
    private String userPhone;
    /**
     * 详细地址
     */
    private String userAddress;

}
