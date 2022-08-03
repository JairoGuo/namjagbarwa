package com.jairoguo.order.domain.model.entity.id;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.jairoguo.common.base.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * @author Jairo Guo
 */

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderNumber implements Id {

    private Long id;
    private Long orderNumber;
    /**
     * 额外订单号
     */
    private String extendOrderId;

    public static OrderNumber create() {
        OrderNumber orderNumber = new OrderNumber();
        orderNumber.orderNumber = IdUtil.getSnowflake().nextId();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = dateTimeFormatter.format(LocalDate.now());
        orderNumber.extendOrderId =date + RandomUtil.randomNumbers(4);
        return orderNumber;
    }

    public static OrderNumber newInstance() {
        return new OrderNumber();
    }

}