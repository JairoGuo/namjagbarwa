package com.jairoguo.seckill.domain.repository;

import com.jairoguo.common.base.Repository;
import com.jairoguo.seckill.domain.model.aggregate.SeckillGoods;
import com.jairoguo.seckill.domain.model.entity.id.SeckillGoodsNumber;

import java.util.List;

/**
 * @author Jairo Guo
 */
public interface SeckillGoodsRepository extends Repository<SeckillGoods, SeckillGoodsNumber> {

    List<SeckillGoods> list();
}
