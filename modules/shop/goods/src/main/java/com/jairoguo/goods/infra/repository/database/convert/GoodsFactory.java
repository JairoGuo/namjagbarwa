package com.jairoguo.goods.infra.repository.database.convert;

import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.infra.repository.database.po.GoodsPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jairo Guo
 */
public class GoodsFactory {

    public static Goods toGoods(GoodsPO goodsPO) {
        Goods goods = Goods.newInstance();
        goods.setName(goodsPO.getName());
        goods.setIntroduction(goods.getIntroduction());

        return goods;
    }

    public static List<Goods> toGoodsList(List<GoodsPO> goodsPOList) {
        ArrayList<Goods> goodsList = new ArrayList<>();
        goodsPOList.forEach((data) -> {
            goodsList.add(toGoods(data));
        });

        return goodsList;
    }
}
