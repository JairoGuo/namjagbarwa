package com.jairoguo.goods.domain.model.entity;

import com.jairoguo.common.base.Entity;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import com.jairoguo.goods.domain.model.value.Price;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品规格属性
 *
 * @author Jairo Guo
 */
@Data
public class SpecsAttribute implements Entity<GoodsNumber> {
    private Long goodsId;

    /**
     * 商品属性索引值 (attr_value|attr_value[|....])
     */
    private String[] sku;

    /**
     * 属性对应的库存
     */
    private Long stock;


    /**
     * 销量
     */
    private Integer sales;


    /**
     * 属性金额
     */
    private Price price;


    /**
     * 图片
     */
    private String image;


    /**
     * 唯一值
     */
    private String unique;


    /**
     * 成本价
     */
    private BigDecimal cost;

}
