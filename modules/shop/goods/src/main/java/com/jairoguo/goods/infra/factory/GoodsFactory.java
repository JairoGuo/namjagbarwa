package com.jairoguo.goods.infra.factory;

import com.jairoguo.common.base.Factory;
import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.model.entity.Specs;
import com.jairoguo.goods.domain.model.value.Price;

/**
 * @author Jairo Guo
 */
public class GoodsFactory implements Factory {
    private GoodsFactory() {
    }
    public static Goods goods() {
        return Goods.create();
    }


    public static Specs specs() {
        return Specs.create();
    }

}
