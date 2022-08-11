package com.jairoguo.goods.domain.repository;


import com.jairoguo.common.base.Repository;
import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.model.entity.SpecsAttribute;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;

import java.util.List;

/**
 * 商品仓储
 *
 * @author Jairo Guo
 */
public interface GoodsRepository extends Repository<Goods, GoodsNumber> {

    List<Goods> list();

    void deductions(GoodsNumber goodsNumber, Long count);

    SpecsAttribute getSpecs(GoodsNumber goodsNumber);

    void increase(GoodsNumber goodsNumber, Long count);
}
