package com.jairoguo.goods.interfaces.rest.dto;

/**
 * @author Jairo Guo
 */
public record BuyDTO(
        Long goodsId,
        Long specsAttributeId,
        Long number
) {
}
