package com.jairoguo.goods.domain.model.value;

import com.jairoguo.common.base.ValueObject;
import lombok.Getter;
import lombok.ToString;

/**
 * 商品规格属性值
 *
 * @author Jairo Guo
 */
@Getter
@ToString
public class SpecsValue implements ValueObject {
    private String specsName;
    private String[] specsValues;

}
