package com.jairoguo.goods.dto;

import java.math.BigDecimal;

/**
 * @author Jairo Guo
 */
public record PriceDTO(
         BigDecimal sellPrice,
         BigDecimal cost,
         BigDecimal vipPrice,
         BigDecimal studentPrice,
         BigDecimal taxPrice
) {
}
