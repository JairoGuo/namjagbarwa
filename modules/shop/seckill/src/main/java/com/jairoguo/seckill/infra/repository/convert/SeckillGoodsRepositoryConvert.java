package com.jairoguo.seckill.infra.repository.convert;

import com.jairoguo.seckill.domain.model.aggregate.SeckillGoods;
import com.jairoguo.seckill.infra.repository.po.SeckillGoodsPO;

import java.util.Collections;
import java.util.List;

/**
 * @author Jairo Guo
 */
public class SeckillGoodsRepositoryConvert {

    public static SeckillGoods toSeckillGoods(SeckillGoodsPO item) {
        if (item == null) {
            return null;
        }
        SeckillGoods result = SeckillGoods.create();
        result.setGoodsId(item.getGoodsId());
        result.setSpecsAttributeId(item.getSpecsAttributeId());
        result.setStock(item.getStock());
        result.setStartDate(item.getStartDate());
        result.setEndDate(item.getEndDate());
        result.setPrice(item.getPrice());
        return result;
    }

    public static SeckillGoodsPO convertToSeckillGoodsPO(SeckillGoods item) {
        if (item == null) {
            return null;
        }
        SeckillGoodsPO result = new SeckillGoodsPO();
        result.setGoodsId(item.getGoodsId());
        result.setSpecsAttributeId(item.getSpecsAttributeId());
        result.setStock(item.getStock());
        result.setStartDate(item.getStartDate());
        result.setEndDate(item.getEndDate());
        result.setPrice(item.getPrice());
        return result;
    }


    public static List<SeckillGoods> convertToList(List<SeckillGoodsPO> item) {
        if (item == null) {
            return Collections.emptyList();
        }

        return item.stream().map(SeckillGoodsRepositoryConvert::toSeckillGoods).toList();
    }
}
