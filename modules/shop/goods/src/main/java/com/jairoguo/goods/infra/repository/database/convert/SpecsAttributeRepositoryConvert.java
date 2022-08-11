package com.jairoguo.goods.infra.repository.database.convert;

import com.jairoguo.goods.domain.model.entity.SpecsAttribute;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import com.jairoguo.goods.domain.model.value.Price;
import com.jairoguo.goods.infra.repository.database.po.SpecsAttributePO;

/**
 * @author Jairo Guo
 */
public class SpecsAttributeRepositoryConvert {


    public static SpecsAttribute toEntity(SpecsAttributePO item) {
        if (item == null) {
            return null;
        }
        SpecsAttribute result = new SpecsAttribute();
        GoodsNumber goodsNumber = GoodsNumber.newInstance();
        goodsNumber.setSpecsAttributeId(item.getId());
        goodsNumber.setId(item.getGoodsId());
        result.setGoodsNumber(goodsNumber);
        result.setStock(item.getStock());
        result.setGoodsId(item.getGoodsId());
        result.setCost(item.getCostPrice());
        result.setSales(item.getSales());
        Price price = new Price();
        price.setCost(item.getCostPrice());
        price.setSellPrice(item.getSellPrice());
        price.setStudentPrice(item.getStudentPrice());
        price.setTaxPrice(item.getTaxPrice());
        price.setVipPrice(item.getVipPrice());
        result.setPrice(price);
        result.setImage(item.getImage());
        result.setSku(item.getSku().split(","));

        return result;
    }
}
