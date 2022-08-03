package com.jairoguo.seckill.interfaces.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Jairo Guo
 */
public record SeckillGoodsDTO(
        Long goodsId,
        Long specsAttributeId,
        Long stock,
        BigDecimal price,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
