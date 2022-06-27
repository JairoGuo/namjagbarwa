package com.jairoguo.goods.domain.model.entity.id;

import com.jairoguo.common.base.Id;
import lombok.Data;

/**
 * 商品编号唯一标识
 *
 * @author Jairo Guo
 */
@Data
public class GoodsNumber implements Id {
    private Long number;
    private Long id;
}
