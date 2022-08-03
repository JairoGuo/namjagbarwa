package com.jairoguo.seckill.interfaces.rest.convert;

import com.jairoguo.seckill.domain.model.aggregate.SeckillGoods;
import com.jairoguo.seckill.interfaces.rest.dto.SeckillGoodsDTO;

/**
 * @author Jairo Guo
 */
public class SeckillGoodsConvert {

    public static SeckillGoods convertToSeckillGoods(SeckillGoodsDTO item) {
        if (item == null) {
            return null;
        }
        SeckillGoods result = SeckillGoods.create();
        result.setGoodsId(item.goodsId());
        result.setSpecsAttributeId(item.specsAttributeId());
        result.setStock(item.stock());
        result.setPrice(item.price());
        result.bindDate(item.startDate(), item.endDate());
        return result;
    }
}
