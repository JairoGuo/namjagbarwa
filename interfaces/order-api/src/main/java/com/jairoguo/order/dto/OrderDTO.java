package com.jairoguo.order.dto;

import lombok.Builder;

import java.math.BigDecimal;

/**
 * @author Jairo Guo
 */

@Builder
public record OrderDTO(
        Long specsAttributeId,
        Long userId,
        BigDecimal price,
        Long total
) {
}
