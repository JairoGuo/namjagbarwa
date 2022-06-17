package com.jairoguo.goods.domain.repository;


import com.jairoguo.common.base.Repository;
import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;

/**
 * 商品仓储
 *
 * @author Jairo Guo
 */
public interface GoodsRepository extends Repository<Goods, GoodsNumber> {
}
