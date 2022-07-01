package com.jairoguo.goods.infra.repository.database.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.model.entity.SpecsAttribute;
import com.jairoguo.goods.domain.model.value.Detail;
import com.jairoguo.goods.domain.model.value.Price;
import com.jairoguo.goods.infra.repository.database.po.GoodsDetailPO;
import com.jairoguo.goods.infra.repository.database.po.GoodsPO;
import com.jairoguo.goods.infra.repository.database.po.SpecsAttributePO;
/**
 * @author Jairo Guo
 */
public class GoodsRepositoryConvert {


    static ObjectMapper objectMapper = new ObjectMapper();

    public static GoodsPO convertToGoodsPO(Goods item) {
        if (item == null) {
            return null;
        }
        GoodsPO result = new GoodsPO();
        result.setGoodsNumber(item.getGoodsNumber().getNumber());
        result.setName(item.getName());
        result.setIntroduction(item.getIntroduction());
        result.setCover(item.getCover());
        return result;
    }

    public static GoodsDetailPO convertToGoodsDetailPO(Detail item) {
        if (item == null) {
            return null;
        }
        GoodsDetailPO result = new GoodsDetailPO();
        result.setContent(item.getContent());
        result.setBarCode(item.getBarCode());
        return result;
    }

    public static SpecsAttributePO convertToSpecsAttributePO(SpecsAttribute item) {
        if (item == null) {
            return null;
        }
        SpecsAttributePO result = new SpecsAttributePO();
        result.setGoodsId(item.getGoodsId());
        String sku;
        try {
            sku = objectMapper.writeValueAsString(item.getSku());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        result.setSku(sku);
        result.setStock(item.getStock());
        result.setSales(item.getSales());
        Price price = item.getPrice();
        result.setSellPrice(price.getSellPrice());
        result.setCostPrice(price.getCost());
        result.setVipPrice(price.getVipPrice());
        result.setStudentPrice(price.getStudentPrice());
        result.setTaxPrice(price.getTaxPrice());
        result.setImage(item.getImage());
        return result;
    }
}
