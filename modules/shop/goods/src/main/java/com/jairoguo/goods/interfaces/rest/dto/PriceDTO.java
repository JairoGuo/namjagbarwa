package com.jairoguo.goods.interfaces.rest.dto;

import java.math.BigDecimal;

/**
 * @author Jairo Guo
 */
public record PriceDTO(
         BigDecimal price,
         BigDecimal cost,
         BigDecimal vipPrice,
         BigDecimal studentPrice,
         BigDecimal taxPrice
) {
}
