package com.jairoguo.goods.vo;

import lombok.Builder;

import java.math.BigDecimal;

/**
 * 商品规格属性
 *
 * @author Jairo Guo
 */
@Builder
public record SpecsAttributeVO(
        Long goodsId,
        Long specsId,

        /**
         * 商品属性索引值 (attr_value|attr_value[|....])
         */
        String[] sku,

        /**
         * 属性对应的库存
         */
        Long stock,


        /**
         * 销量
         */
        Integer sales,


        /**
         * 属性金额
         */
        PriceVO price,


        /**
         * 图片
         */
        String image,

        /**
         * 成本价
         */
        BigDecimal cost

) {


}
