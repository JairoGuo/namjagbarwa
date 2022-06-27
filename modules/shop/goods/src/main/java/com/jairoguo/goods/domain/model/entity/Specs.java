package com.jairoguo.goods.domain.model.entity;


import com.jairoguo.common.base.Entity;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import com.jairoguo.goods.domain.model.value.SpecsAttribute;
import com.jairoguo.goods.domain.model.value.SpecsValue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 商品规格
 *
 * @author Jairo Guo
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Specs implements Entity<GoodsNumber> {
    private Boolean multiSpecs;
    private List<SpecsAttribute> specsAttributeList;
    private List<SpecsValue> specsValueList;

}
