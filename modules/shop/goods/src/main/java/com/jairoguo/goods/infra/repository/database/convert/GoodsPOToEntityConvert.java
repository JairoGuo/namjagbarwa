package com.jairoguo.goods.infra.repository.database.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.model.entity.SpecsAttribute;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import com.jairoguo.goods.domain.model.value.Price;
import com.jairoguo.goods.infra.repository.database.po.GoodsPO;
import com.jairoguo.goods.infra.repository.database.po.SpecsAttributePO;

import java.util.List;

/**
 * @author Jairo Guo
 */
public class GoodsPOToEntityConvert {

    static ObjectMapper objectMapper = new ObjectMapper();
    public static List<Goods> convertToGoodsList(List<GoodsPO> item) {
        if (item == null) {
            return null;
        }
        return item.stream().map(GoodsPOToEntityConvert::convertToGoods).toList();
    }

    public static Goods convertToGoods(GoodsPO item) {
        if (item == null) {
            return null;
        }
        Goods result = Goods.newInstance();
        GoodsNumber goodsNumber = GoodsNumber.newInstance();
        goodsNumber.setNumber(item.getGoodsNumber());
        goodsNumber.setId(item.getId());
        result.setGoodsNumber(goodsNumber);
        result.setName(item.getName());
        result.setIntroduction(item.getIntroduction());
        result.setCover(item.getCover());
        return result;
    }


    public static SpecsAttribute convertToSpecsAttribute(SpecsAttributePO specsAttributePO) {
        SpecsAttribute specsAttribute = new SpecsAttribute();

        specsAttribute.setGoodsId(specsAttributePO.getGoodsId());
        Price price = new Price();
        price.setSellPrice(specsAttributePO.getSellPrice());
        price.setStudentPrice(specsAttributePO.getStudentPrice());
        price.setTaxPrice(specsAttributePO.getTaxPrice());
        price.setVipPrice(specsAttributePO.getVipPrice());
        price.setCost(specsAttributePO.getCostPrice());
        specsAttribute.setPrice(price);
        specsAttribute.setCost(specsAttributePO.getCostPrice());
        specsAttribute.setImage(specsAttributePO.getImage());
        String[] sku;
        try {
            sku = objectMapper.readValue(specsAttributePO.getSku(), String[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        specsAttribute.setSku(sku);
        specsAttribute.setStock(specsAttributePO.getStock());

        return specsAttribute;
    }

    public static List<SpecsAttribute> convertToSpecsAttributeList(List<SpecsAttributePO> item) {
        if (item == null) {
            return null;
        }

        return item.stream().map(GoodsPOToEntityConvert::convertToSpecsAttribute).toList();
    }

}
