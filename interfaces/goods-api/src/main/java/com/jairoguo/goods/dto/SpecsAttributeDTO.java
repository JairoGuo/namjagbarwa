package com.jairoguo.goods.dto;

import java.math.BigDecimal;

/**
 * @author Jairo Guo
 */
public record SpecsAttributeDTO(
        String[] sku,
        Long stock,
        PriceDTO price,
        String image,
        BigDecimal cost
) {

}
