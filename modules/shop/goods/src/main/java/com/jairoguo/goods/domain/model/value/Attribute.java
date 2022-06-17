package com.jairoguo.goods.domain.model.value;

import com.jairoguo.common.base.ValueObject;
import lombok.Getter;
import lombok.ToString;

/**
 * 商品属性
 *
 * @author Jairo Guo
 */
@Getter
@ToString
public class Attribute implements ValueObject {
    private Long id;

    /** 商品ID */
    private Long goodsId;

    /**
     * 属性名
     */
    private String attributeName;

    /**
     * 属性值
     */
    private String attributeValues;

}
