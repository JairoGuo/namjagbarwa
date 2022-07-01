package com.jairoguo.goods.domain.model.entity;


import com.jairoguo.common.base.Entity;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 商品规格
 *
 * @author Jairo Guo
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Specs implements Entity<GoodsNumber> {
    private Boolean multiSpecs;
    private List<SpecsAttribute> specsAttributeList;

    public static Specs create() {
        return new Specs();
    }

}
