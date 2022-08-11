package com.jairoguo.goods.application.bo;

import com.jairoguo.goods.domain.model.aggregate.Goods;
import lombok.Builder;

/**
 * @author Jairo Guo
 */

@Builder
public record GoodsBO(
        Goods goods,
        Long storeId
) {
}