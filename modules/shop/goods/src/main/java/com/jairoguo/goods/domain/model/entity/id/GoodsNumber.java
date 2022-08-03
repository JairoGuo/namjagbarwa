package com.jairoguo.goods.domain.model.entity.id;

import cn.hutool.core.util.IdUtil;
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
    private Long specsAttributeId;

    private GoodsNumber() {
    }

    public static GoodsNumber newInstance() {
        return new GoodsNumber();
    }

    public static GoodsNumber create() {
        GoodsNumber goodsNumber = new GoodsNumber();
        goodsNumber.setNumber(IdUtil.getSnowflake().nextId());
        return goodsNumber;


    }
}
