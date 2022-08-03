package com.jairoguo.seckill.domain.model.entity.id;

import com.jairoguo.common.base.Id;
import lombok.Data;

/**
 * @author Jairo Guo
 */
@Data
public class SeckillGoodsNumber implements Id {
    private Long id;
    private Long goodsId;
    private Long specsAttributeId;
}
