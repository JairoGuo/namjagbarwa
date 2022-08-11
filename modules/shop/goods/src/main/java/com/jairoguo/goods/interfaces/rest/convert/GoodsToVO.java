package com.jairoguo.goods.interfaces.rest.convert;

import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.model.entity.SpecsAttribute;
import com.jairoguo.goods.domain.model.value.Price;
import com.jairoguo.goods.vo.GoodsVO;
import com.jairoguo.goods.vo.PriceVO;
import com.jairoguo.goods.vo.SpecsAttributeVO;

import java.util.List;

/**
 * @author Jairo Guo
 */
public class GoodsToVO {

    public static GoodsVO convertToGoodsVO(Goods item) {
        if (item == null) {
            return null;
        }
        List<SpecsAttributeVO> specsAttributeVOList = null;
        if (item.getSpecs() != null) {
            specsAttributeVOList = convertToList(item.getSpecs().getSpecsAttributeList());
        }
        return new GoodsVO(item.getName(), item.getIntroduction(), item.getCover(), specsAttributeVOList);
    }

    public static List<GoodsVO> convertToGoodsList(List<Goods> item) {
        if (item == null) {
            return null;
        }
        return item.stream().map(GoodsToVO::convertToGoodsVO).toList();
    }


    public static List<SpecsAttributeVO> convertToList(List<SpecsAttribute> item) {
        if (item == null) {
            return null;
        }
        return item.stream().map(GoodsToVO::convertToSpecsAttributeVO).toList();
    }


    public static SpecsAttributeVO convertToSpecsAttributeVO(SpecsAttribute item) {
        if (item == null) {
            return null;
        }
        return SpecsAttributeVO.builder()
                .goodsId(item.getGoodsId())
                .specsId(item.getGoodsNumber().getSpecsAttributeId())
                .sku(item.getSku())
                .stock(item.getStock())
                .sales(item.getSales())
                .price(convertToPriceVO(item.getPrice()))
                .image(item.getImage())
                .cost(item.getCost())
                .build();
    }

    public static PriceVO convertToPriceVO(Price item) {
        if (item == null) {
            return null;
        }
        return new PriceVO(item.getSellPrice(), item.getCost(), item.getVipPrice(), item.getStudentPrice(), item.getTaxPrice());
    }


}
