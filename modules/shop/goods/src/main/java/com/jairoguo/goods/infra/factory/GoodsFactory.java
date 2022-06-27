package com.jairoguo.goods.infra.factory;

import com.jairoguo.common.base.Factory;
import com.jairoguo.goods.domain.model.aggregate.Goods;

/**
 * @author Jairo Guo
 */
public class GoodsFactory implements Factory {
    private GoodsFactory() {
    }
    public static Goods goods() {
        return Goods.create();
    }

}
