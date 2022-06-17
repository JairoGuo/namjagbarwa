package com.jairoguo.goods.domain.model.value;

import com.jairoguo.common.base.ValueObject;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 商品规格属性
 *
 * @author Jairo Guo
 */
@Getter
@ToString
public class SpecsAttribute implements ValueObject {
    private Long goodsId;

    /**
     * 商品属性索引值 (attr_value|attr_value[|....])
     */
    private String[] sku;

    /**
     * 属性对应的库存
     */
    private Integer stock;


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
