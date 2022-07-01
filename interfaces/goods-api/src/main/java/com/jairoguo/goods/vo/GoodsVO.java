package com.jairoguo.goods.vo;

import java.util.List;

/**
 * @author Jairo Guo
 */

public record GoodsVO(
        String name,
        String introduction,
        String cover,
        List<SpecsAttributeVO> specsAttributeList
        ) {

}
