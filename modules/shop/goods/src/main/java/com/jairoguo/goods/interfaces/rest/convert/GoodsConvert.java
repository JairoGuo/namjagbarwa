package com.jairoguo.goods.interfaces.rest.convert;

import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.model.entity.Specs;
import com.jairoguo.goods.domain.model.entity.SpecsAttribute;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import com.jairoguo.goods.domain.model.value.Price;
import com.jairoguo.goods.domain.model.value.SpecsValue;
import com.jairoguo.goods.dto.GoodsDTO;
import com.jairoguo.goods.dto.PriceDTO;
import com.jairoguo.goods.dto.SpecsAttributeDTO;
import com.jairoguo.goods.dto.SpecsDTO;

import java.util.List;

/**
 * @author Jairo Guo
 */
public class GoodsConvert {

    public static Goods convertToGoods(GoodsDTO item) {
        if (item == null) {
            return null;
        }
        Goods result = Goods.newInstance();
        result.setGoodsNumber(GoodsNumber.create());
        result.setName(item.name());
        result.setCover(item.cover());
        result.setIntroduction(item.introduction());
        Specs specs = Specs.create();
        specs.setMultiSpecs(item.multiSpecs());
        List<SpecsAttribute> specsAttributesList = item.specsAttributeList().stream().map(GoodsConvert::convertToSpecsAttribute).toList();
        specs.setSpecsAttributeList(specsAttributesList);
        result.setSpecs(specs);
        result.setStoreId(item.storeId());
        return result;
    }


    public static SpecsValue convertToSpecsValue(SpecsDTO item) {
        if (item == null) {
            return null;
        }
        SpecsValue result = new SpecsValue();
        result.setSpecsName(item.specsName());
        result.setSpecsValues(item.specsValue());
        return result;
    }

    public static SpecsAttribute convertToSpecsAttribute(SpecsAttributeDTO item) {
        if (item == null) {
            return null;
        }
        SpecsAttribute result = new SpecsAttribute();
        result.setSku(item.sku());
        result.setStock(item.stock());
        result.setPrice(convertToPrice(item.price()));
        result.setImage(item.image());
        result.setCost(item.cost());
        return result;
    }

    public static Price convertToPrice(PriceDTO item) {
        if (item == null) {
            return null;
        }
        Price result = new Price();
        result.setSellPrice(item.sellPrice());
        result.setCost(item.cost());
        result.setVipPrice(item.vipPrice());
        result.setStudentPrice(item.studentPrice());
        result.setTaxPrice(item.taxPrice());
        return result;
    }


}
